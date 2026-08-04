function EleveForm({
  values,
  isEditing,
  onChange,
  onSubmit,
  onCancelEdit,
}) {
  return (
    <form onSubmit={onSubmit}>
      <div>
        <label htmlFor="nom">Nom</label>
        <input
          id="nom"
          name="nom"
          type="text"
          value={values.nom}
          onChange={onChange}
          required
        />
      </div>

      <div>
        <label htmlFor="prenom">Prenom</label>
        <input
          id="prenom"
          name="prenom"
          type="text"
          value={values.prenom}
          onChange={onChange}
          required
        />
      </div>

      <div>
        <label htmlFor="dateNaissance">Date de naissance</label>
        <input
          id="dateNaissance"
          name="dateNaissance"
          type="date"
          value={values.dateNaissance}
          onChange={onChange}
          required
        />
      </div>

      <div>
        <label htmlFor="adresse">Adresse</label>
        <input
          id="adresse"
          name="adresse"
          type="text"
          value={values.adresse}
          onChange={onChange}
        />
      </div>

      <div>
        <label htmlFor="email">Email</label>
        <input
          id="email"
          name="email"
          type="email"
          value={values.email}
          onChange={onChange}
        />
      </div>

      <div>
        <label htmlFor="telephone">Telephone</label>
        <input
          id="telephone"
          name="telephone"
          type="text"
          value={values.telephone}
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

export default EleveForm
