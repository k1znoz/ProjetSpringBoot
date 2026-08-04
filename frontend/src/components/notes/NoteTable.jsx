function NoteTable({ notes, elevesById, matieresById, onEdit, onDelete }) {
  const getEleveLabel = (idEleve) => {
    const eleve = elevesById.get(idEleve)
    if (!eleve) {
      return ''
    }
    return `${eleve.nom} ${eleve.prenom}`
  }

  const getMatiereLabel = (idMatiere) => {
    const matiere = matieresById.get(idMatiere)
    return matiere ? matiere.nomMatiere : ''
  }

  return (
    <table>
      <thead>
        <tr>
          <th>Eleve</th>
          <th>Matiere</th>
          <th>Valeur</th>
          <th>Date</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {notes.map((note) => (
          <tr key={note.idNote}>
            <td>{getEleveLabel(note.idEleve)}</td>
            <td>{getMatiereLabel(note.idMatiere)}</td>
            <td>{note.valeur}</td>
            <td>{note.dateNote}</td>
            <td>
              <button type="button" onClick={() => onEdit(note.idNote)}>
                Modifier
              </button>
              <button
                type="button"
                onClick={() => {
                  if (window.confirm('Confirmer la suppression ?')) {
                    onDelete(note.idNote)
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

export default NoteTable
