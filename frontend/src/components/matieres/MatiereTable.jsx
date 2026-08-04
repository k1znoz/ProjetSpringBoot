function MatiereTable({ matieres, onEdit, onDelete }) {
  return (
    <table>
      <thead>
        <tr>
          <th>Nom</th>
          <th>Coefficient</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {matieres.map((matiere) => (
          <tr key={matiere.idMatiere}>
            <td>{matiere.nomMatiere}</td>
            <td>{matiere.coefficient}</td>
            <td>
              <button type="button" onClick={() => onEdit(matiere.idMatiere)}>
                Modifier
              </button>
              <button
                type="button"
                onClick={() => {
                  if (window.confirm('Confirmer la suppression ?')) {
                    onDelete(matiere.idMatiere)
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

export default MatiereTable
