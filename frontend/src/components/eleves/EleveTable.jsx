function EleveTable({ eleves, onEdit, onDelete }) {
  return (
    <table>
      <thead>
        <tr>
          <th>Nom</th>
          <th>Prenom</th>
          <th>Classe</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {eleves.map((eleve) => (
          <tr key={eleve.idEleve}>
            <td>{eleve.nom}</td>
            <td>{eleve.prenom}</td>
            <td>{eleve.classe?.nomClasse ?? ''}</td>
            <td>
              <button type="button" onClick={() => onEdit(eleve.idEleve)}>
                Modifier
              </button>
              <button
                type="button"
                onClick={() => {
                  if (window.confirm('Confirmer la suppression ?')) {
                    onDelete(eleve.idEleve)
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

export default EleveTable
