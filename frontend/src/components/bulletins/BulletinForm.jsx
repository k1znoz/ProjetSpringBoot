function BulletinForm({
  values,
  eleves,
  isEditing,
  onChange,
  onSubmit,
  onCancelEdit,
}) {
  return (
    <form onSubmit={onSubmit}>
      <div>
        <label htmlFor="idEleve">Eleve</label>
        <select
          id="idEleve"
          name="idEleve"
          value={values.idEleve}
          onChange={onChange}
          required
        >
          <option value="">Selectionner un eleve</option>
          {eleves.map((eleve) => (
            <option key={eleve.idEleve} value={eleve.idEleve}>
              {`${eleve.nom} ${eleve.prenom}`}
            </option>
          ))}
        </select>
      </div>

      <div>
        <label htmlFor="trimestre">Trimestre</label>
        <select
          id="trimestre"
          name="trimestre"
          value={values.trimestre}
          onChange={onChange}
          required
        >
          <option value="">Selectionner un trimestre</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
        </select>
      </div>

      <div>
        <label htmlFor="anneeScolaire">Annee scolaire</label>
        <input
          id="anneeScolaire"
          name="anneeScolaire"
          type="text"
          value={values.anneeScolaire}
          onChange={onChange}
          required
        />
      </div>

      <div>
        <label htmlFor="moyenneGenerale">Moyenne generale</label>
        <input
          id="moyenneGenerale"
          name="moyenneGenerale"
          type="number"
          step="0.01"
          value={values.moyenneGenerale}
          onChange={onChange}
        />
      </div>

      <div>
        <label htmlFor="appreciation">Appreciation</label>
        <input
          id="appreciation"
          name="appreciation"
          type="text"
          value={values.appreciation}
          onChange={onChange}
        />
      </div>

      <button type="submit">{isEditing ? 'Mettre a jour' : 'Ajouter'}</button>
      {isEditing && (
        <button type="button" onClick={onCancelEdit}>
          Annuler
        </button>
      )}
    </form>
  )
}

export default BulletinForm
