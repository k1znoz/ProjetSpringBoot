function BulletinTable({ bulletins, elevesById, onEdit, onDelete }) {
  const getEleveLabel = (idEleve) => {
    const eleve = elevesById.get(idEleve)
    if (!eleve) {
      return ''
    }
    return `${eleve.nom} ${eleve.prenom}`
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
