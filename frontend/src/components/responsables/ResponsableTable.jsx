function ResponsableTable({ responsables, onEdit, onDelete }) {
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
        {responsables.map((responsable) => (
          <tr key={responsable.idResponsable}>
            <td>{responsable.nom}</td>
            <td>{responsable.prenom}</td>
            <td>{responsable.email}</td>
            <td>{responsable.telephone}</td>
            <td>
              <button type="button" onClick={() => onEdit(responsable.idResponsable)}>
                Modifier
              </button>
              <button
                type="button"
                onClick={() => {
                  if (window.confirm('Confirmer la suppression ?')) {
                    onDelete(responsable.idResponsable)
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

export default ResponsableTable
