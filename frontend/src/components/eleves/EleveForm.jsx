function EleveForm({
  values,
  isEditing,
  photoPreviewUrl,
  selectedPhotoName,
  onChange,
  onFileChange,
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

      <div>
        <label htmlFor="photo">Photo</label>
        <input
          id="photo"
          name="photo"
          type="file"
          accept="image/*"
          onChange={onFileChange}
        />
        {isEditing && (
          <div>
            <p>Photo actuelle</p>
            {photoPreviewUrl ? (
              <img
                src={photoPreviewUrl}
                alt={`Photo de ${values.prenom} ${values.nom}`}
                className="h-20 w-20 rounded object-cover"
              />
            ) : (
              <span>Aucune photo</span>
            )}
          </div>
        )}
        {selectedPhotoName && <p>Nouvelle photo: {selectedPhotoName}</p>}
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
