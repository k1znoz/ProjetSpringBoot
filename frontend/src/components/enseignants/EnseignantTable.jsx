function EnseignantTable({ enseignants, onEdit, onDelete }) {
  return (
    <table>
      <thead>
        <tr>
          <th>Nom</th>
          <th>Prenom</th>
          <th>Email</th>
          <th>Telephone</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {enseignants.map((enseignant) => (
          <tr key={enseignant.idEnseignant}>
            <td>{enseignant.nom}</td>
            <td>{enseignant.prenom}</td>
            <td>{enseignant.email}</td>
            <td>{enseignant.telephone}</td>
            <td>
              <button type="button" onClick={() => onEdit(enseignant.idEnseignant)}>
                Modifier
              </button>
              <button
                type="button"
                onClick={() => {
                  if (window.confirm('Confirmer la suppression ?')) {
                    onDelete(enseignant.idEnseignant)
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

export default EnseignantTable
