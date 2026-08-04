import { downloadPdf } from '../../services/bulletinService'

function BulletinTable({ bulletins, elevesById, onEdit, onDelete }) {
  const getEleveLabel = (idEleve) => {
    const eleve = elevesById.get(idEleve)
    if (!eleve) {
      return ''
    }
    return `${eleve.nom} ${eleve.prenom}`
  }

  const handleDownloadPdf = async (idBulletin) => {
    try {
      const response = await downloadPdf(idBulletin)
      const pdfBlob = new Blob([response.data], { type: 'application/pdf' })
      const downloadUrl = window.URL.createObjectURL(pdfBlob)
      const link = document.createElement('a')

      link.href = downloadUrl
      link.download = `bulletin_${idBulletin}.pdf`
      document.body.appendChild(link)
      link.click()
      link.remove()
      window.URL.revokeObjectURL(downloadUrl)
    } catch (error) {
      console.error('Impossible de telecharger le bulletin PDF.', error)
    }
  }

  return (
    <table>
      <thead>
        <tr>
          <th>Eleve</th>
          <th>Trimestre</th>
          <th>Moyenne</th>
          <th>Appreciation</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {bulletins.map((bulletin) => (
          <tr key={bulletin.idBulletin}>
            <td>{getEleveLabel(bulletin.idEleve)}</td>
            <td>{bulletin.trimestre}</td>
            <td>{bulletin.moyenneGenerale ?? ''}</td>
            <td>{bulletin.appreciation ?? ''}</td>
            <td>
              <button type="button" onClick={() => onEdit(bulletin.idBulletin)}>
                Modifier
              </button>
              <button
                type="button"
                onClick={() => handleDownloadPdf(bulletin.idBulletin)}
                className="!ml-2 !inline-flex !items-center !rounded-lg !border !border-blue-200 !bg-blue-50 !px-3 !py-1.5 !text-xs !font-semibold !text-blue-700 !transition hover:!border-blue-300 hover:!bg-blue-100 hover:!text-blue-800"
              >
                Télécharger PDF
              </button>
              <button
                type="button"
                onClick={() => {
                  if (window.confirm('Confirmer la suppression ?')) {
                    onDelete(bulletin.idBulletin)
                  }
                }}
              >
                Supprimer
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  )
}

export default BulletinTable
