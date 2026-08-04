function ClasseForm({
  values,
  isEditing,
  onChange,
  onSubmit,
  onCancelEdit,
}) {
  return (
    <form onSubmit={onSubmit}>
      <div>
        <label htmlFor="nomClasse">Nom de la classe</label>
        <input
          id="nomClasse"
          name="nomClasse"
          type="text"
          value={values.nomClasse}
          onChange={onChange}
          required
        />
      </div>

      <div>
        <label htmlFor="niveau">Niveau</label>
        <input
          id="niveau"
          name="niveau"
          type="text"
          value={values.niveau}
          onChange={onChange}
          required
        />
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

      <button type="submit">{isEditing ? 'Mettre a jour' : 'Ajouter'}</button>
      {isEditing && (
        <button type="button" onClick={onCancelEdit}>
          Annuler
        </button>
      )}
    </form>
  )
}

export default ClasseForm
