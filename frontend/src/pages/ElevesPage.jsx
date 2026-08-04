import { useCallback, useEffect, useState } from 'react'
import EleveForm from '../components/eleves/EleveForm'
import EleveTable from '../components/eleves/EleveTable'
import { create, getAll, getById, remove, update } from '../services/eleveService'

const EMPTY_FORM = {
  nom: '',
  prenom: '',
  dateNaissance: '',
  adresse: '',
  email: '',
  telephone: '',
}

function ElevesPage() {
  const [eleves, setEleves] = useState([])
  const [formValues, setFormValues] = useState(EMPTY_FORM)
  const [editingId, setEditingId] = useState(null)
  const [isLoading, setIsLoading] = useState(true)
  const [errorMessage, setErrorMessage] = useState('')

  const loadEleves = useCallback(async () => {
    setIsLoading(true)
    setErrorMessage('')

    try {
      const response = await getAll()
      setEleves(response.data)
    } catch {
      setErrorMessage('Impossible de charger les eleves.')
      setEleves([])
    } finally {
      setIsLoading(false)
    }
  }, [])

  useEffect(() => {
    loadEleves()
  }, [])

  const handleChange = (event) => {
    const { name, value } = event.target
    setFormValues((current) => ({ ...current, [name]: value }))
  }

  const resetForm = () => {
    setFormValues(EMPTY_FORM)
    setEditingId(null)
  }

  const handleSubmit = async (event) => {
    event.preventDefault()
    setErrorMessage('')

    try {
      if (editingId === null) {
        await create(formValues)
      } else {
        await update(editingId, formValues)
      }

      resetForm()
      await loadEleves()
    } catch {
      setErrorMessage('Impossible de sauvegarder cet eleve.')
    }
  }

  const handleEdit = async (id) => {
    setErrorMessage('')

    try {
      const response = await getById(id)
      const eleve = response.data

      setFormValues({
        nom: eleve.nom ?? '',
        prenom: eleve.prenom ?? '',
        dateNaissance: eleve.dateNaissance ?? '',
        adresse: eleve.adresse ?? '',
        email: eleve.email ?? '',
        telephone: eleve.telephone ?? '',
      })
      setEditingId(id)
    } catch {
      setErrorMessage('Impossible de charger cet eleve.')
    }
  }

  const handleDelete = async (id) => {
    setErrorMessage('')

    try {
      await remove(id)

      if (editingId === id) {
        resetForm()
      }

      await loadEleves()
    } catch {
      setErrorMessage('Impossible de supprimer cet eleve.')
    }
  }

  return (
    <div className="crud-page">
      <h1 className="page-title">Liste des élèves</h1>
      {isLoading && <p className="status-loading">Chargement...</p>}
      {errorMessage && <p className="status-error">{errorMessage}</p>}

      <EleveForm
        values={formValues}
        isEditing={editingId !== null}
        onChange={handleChange}
        onSubmit={handleSubmit}
        onCancelEdit={resetForm}
      />

      {!isLoading && eleves.length === 0 ? (
        <p className="status-empty">Aucun élève.</p>
      ) : (
        !isLoading && (
          <div className="overflow-x-auto rounded-xl">
            <EleveTable eleves={eleves} onEdit={handleEdit} onDelete={handleDelete} />
          </div>
        )
      )}
    </div>
  )
}

export default ElevesPage
