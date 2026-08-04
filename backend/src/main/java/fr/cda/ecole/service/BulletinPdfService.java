package fr.cda.ecole.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cda.ecole.entity.Bulletin;

@Service
public class BulletinPdfService {

    private static final DecimalFormat AVERAGE_FORMAT = new DecimalFormat("0.00");

    @Transactional(readOnly = true)
    public byte[] generatePdf(Bulletin bulletin) {
        try {
            return buildPdf(bulletin);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to generate bulletin PDF", exception);
        }
    }

    private byte[] buildPdf(Bulletin bulletin) throws IOException {
        List<PdfLine> lines = buildLines(bulletin);
        String contentStream = buildContentStream(lines);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<Integer> offsets = new ArrayList<>();

        writeLine(outputStream, "%PDF-1.4");

        offsets.add(outputStream.size());
        writeObject(outputStream, "1 0 obj << /Type /Catalog /Pages 2 0 R >> endobj");

        offsets.add(outputStream.size());
        writeObject(outputStream, "2 0 obj << /Type /Pages /Kids [3 0 R] /Count 1 >> endobj");

        offsets.add(outputStream.size());
        writeObject(outputStream, "3 0 obj << /Type /Page /Parent 2 0 R /MediaBox [0 0 595 842] /Resources << /Font << /F1 4 0 R >> >> /Contents 5 0 R >> endobj");

        offsets.add(outputStream.size());
        writeObject(outputStream, "4 0 obj << /Type /Font /Subtype /Type1 /BaseFont /Helvetica >> endobj");

        byte[] contentBytes = contentStream.getBytes(StandardCharsets.ISO_8859_1);
        offsets.add(outputStream.size());
        writeLine(outputStream, "5 0 obj << /Length " + contentBytes.length + " >> stream");
        outputStream.write(contentBytes);
        writeLine(outputStream, "endstream");
        writeLine(outputStream, "endobj");

        int xrefOffset = outputStream.size();
        writeLine(outputStream, "xref");
        writeLine(outputStream, "0 6");
        writeLine(outputStream, String.format("%010d 65535 f ", 0));
        for (Integer offset : offsets) {
            writeLine(outputStream, String.format("%010d 00000 n ", offset));
        }
        writeLine(outputStream, "trailer << /Size 6 /Root 1 0 R >>");
        writeLine(outputStream, "startxref");
        writeLine(outputStream, String.valueOf(xrefOffset));
        writeLine(outputStream, "%%EOF");

        return outputStream.toByteArray();
    }

    private List<PdfLine> buildLines(Bulletin bulletin) {
        List<PdfLine> lines = new ArrayList<>();
        lines.add(new PdfLine("Bulletin scolaire", 18));
        lines.add(new PdfLine("", 11));
        lines.add(new PdfLine("Eleve : " + formatEleve(bulletin), 11));
        lines.add(new PdfLine("Trimestre : " + safeValue(bulletin.getTrimestre()), 11));
        lines.add(new PdfLine("Annee scolaire : " + safeValue(bulletin.getAnneeScolaire()), 11));
        lines.add(new PdfLine("Moyenne generale : " + formatAverage(bulletin), 11));
        lines.add(new PdfLine("Appreciation :", 11));

        String appreciation = normalizeText(bulletin.getAppreciation());
        if (appreciation.isBlank()) {
            lines.add(new PdfLine("Aucune appreciation.", 11));
        } else {
            for (String wrappedLine : wrapText(appreciation, 72)) {
                lines.add(new PdfLine(wrappedLine, 11));
            }
        }

        return lines;
    }

    private String buildContentStream(List<PdfLine> lines) {
        StringBuilder builder = new StringBuilder();
        int y = 780;

        builder.append("BT\n");
        for (int index = 0; index < lines.size(); index++) {
            PdfLine line = lines.get(index);
            if (index == 0) {
                builder.append("/F1 ").append(line.fontSize()).append(" Tf\n");
                builder.append(String.format("1 0 0 1 72 %d Tm\n", y));
                builder.append("(").append(escapePdfText(line.text())).append(") Tj\n");
                y -= 34;
                continue;
            }

            if (line.text().isBlank()) {
                y -= 12;
                continue;
            }

            builder.append("/F1 ").append(line.fontSize()).append(" Tf\n");
            builder.append(String.format("1 0 0 1 72 %d Tm\n", y));
            builder.append("(").append(escapePdfText(line.text())).append(") Tj\n");
            y -= 18;
        }
        builder.append("ET\n");

        return builder.toString();
    }

    private String formatEleve(Bulletin bulletin) {
        if (bulletin.getEleve() == null) {
            return "Non renseigne";
        }

        String prenom = normalizeText(bulletin.getEleve().getPrenom());
        String nom = normalizeText(bulletin.getEleve().getNom());

        if (prenom.isBlank() && nom.isBlank()) {
            return "Non renseigne";
        }

        if (prenom.isBlank()) {
            return nom;
        }

        if (nom.isBlank()) {
            return prenom;
        }

        return prenom + " " + nom;
    }

    private String formatAverage(Bulletin bulletin) {
        if (bulletin.getMoyenneGenerale() == null) {
            return "Non renseignee";
        }

        return AVERAGE_FORMAT.format(bulletin.getMoyenneGenerale());
    }

    private String safeValue(Object value) {
        return value == null ? "Non renseigne" : normalizeText(String.valueOf(value));
    }

    private String normalizeText(String value) {
        return value == null ? "" : value.trim();
    }

    private List<String> wrapText(String text, int maxLength) {
        List<String> wrappedLines = new ArrayList<>();

        String[] words = text.split("\\s+");
        StringBuilder currentLine = new StringBuilder();
        for (String word : words) {
            if (currentLine.isEmpty()) {
                currentLine.append(word);
                continue;
            }

            if (currentLine.length() + 1 + word.length() <= maxLength) {
                currentLine.append(' ').append(word);
            } else {
                wrappedLines.add(currentLine.toString());
                currentLine.setLength(0);
                currentLine.append(word);
            }
        }

        if (!currentLine.isEmpty()) {
            wrappedLines.add(currentLine.toString());
        }

        if (wrappedLines.isEmpty()) {
            wrappedLines.add("");
        }

        return wrappedLines;
    }

    private String escapePdfText(String text) {
        return text
                .replace("\\", "\\\\")
                .replace("(", "\\(")
                .replace(")", "\\)");
    }

    private void writeObject(ByteArrayOutputStream outputStream, String value) throws IOException {
        writeLine(outputStream, value);
    }

    private void writeLine(ByteArrayOutputStream outputStream, String value) throws IOException {
        outputStream.write(value.getBytes(StandardCharsets.ISO_8859_1));
        outputStream.write('\r');
        outputStream.write('\n');
    }

    private record PdfLine(String text, int fontSize) {
    }
}