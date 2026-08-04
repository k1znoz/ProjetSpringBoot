function NoteForm({
  values,
  eleves,
  matieres,
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
        <label htmlFor="idMatiere">Matiere</label>
        <select
          id="idMatiere"
          name="idMatiere"
          value={values.idMatiere}
          onChange={onChange}
          required
        >
          <option value="">Selectionner une matiere</option>
          {matieres.map((matiere) => (
            <option key={matiere.idMatiere} value={matiere.idMatiere}>
              {matiere.nomMatiere}
            </option>
          ))}
        </select>
      </div>

      <div>
        <label htmlFor="valeur">Valeur</label>
        <input
          id="valeur"
          name="valeur"
          type="number"
          step="0.01"
          value={values.valeur}
          onChange={onChange}
          required
        />
      </div>

      <div>
        <label htmlFor="dateNote">Date</label>
        <input
          id="dateNote"
          name="dateNote"
          type="date"
          value={values.dateNote}
          onChange={onChange}
          required
        />
      </div>

      <div>
        <label htmlFor="typeEvaluation">Type evaluation</label>
        <input
          id="typeEvaluation"
          name="typeEvaluation"
          type="text"
          value={values.typeEvaluation}
          onChange={onChange}
          required
        />
      </div>

      <div>
        <label htmlFor="commentaire">Commentaire</label>
        <input
          id="commentaire"
          name="commentaire"
          type="text"
          value={values.commentaire}
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

export default NoteForm
