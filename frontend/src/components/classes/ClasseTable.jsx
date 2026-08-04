function ClasseTable({ classes, onEdit, onDelete }) {
  return (
    <table>
      <thead>
        <tr>
          <th>Nom</th>
          <th>Niveau</th>
          <th>Annee scolaire</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {classes.map((classe) => (
          <tr key={classe.idClasse}>
            <td>{classe.nomClasse}</td>
            <td>{classe.niveau}</td>
            <td>{classe.anneeScolaire}</td>
            <td>
              <button type="button" onClick={() => onEdit(classe.idClasse)}>
                Modifier
              </button>
              <button
                type="button"
                onClick={() => {
                  if (window.confirm('Confirmer la suppression ?')) {
                    onDelete(classe.idClasse)
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

export default ClasseTable
