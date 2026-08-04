function MatiereForm({
  values,
  isEditing,
  onChange,
  onSubmit,
  onCancelEdit,
}) {
  return (
    <form onSubmit={onSubmit}>
      <div>
        <label htmlFor="nomMatiere">Nom de la matiere</label>
        <input
          id="nomMatiere"
          name="nomMatiere"
          type="text"
          value={values.nomMatiere}
          onChange={onChange}
          required
        />
      </div>

      <div>
        <label htmlFor="coefficient">Coefficient</label>
        <input
          id="coefficient"
          name="coefficient"
          type="number"
          step="0.01"
          value={values.coefficient}
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

export default MatiereForm
