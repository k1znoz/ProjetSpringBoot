import { useCallback, useEffect, useState } from 'react'
import EnseignantForm from '../components/enseignants/EnseignantForm'
import EnseignantTable from '../components/enseignants/EnseignantTable'
import {
  create,
  getAll,
  getById,
  remove,
  update,
} from '../services/enseignantService'

const EMPTY_FORM = {
  nom: '',
  prenom: '',
  email: '',
  telephone: '',
}

function EnseignantsPage() {
  const [enseignants, setEnseignants] = useState([])
  const [formValues, setFormValues] = useState(EMPTY_FORM)
  const [editingId, setEditingId] = useState(null)
  const [isLoading, setIsLoading] = useState(true)
  const [errorMessage, setErrorMessage] = useState('')

  const loadEnseignants = useCallback(async () => {
    setIsLoading(true)
    setErrorMessage('')

    try {
      const response = await getAll()
      setEnseignants(response.data)
    } catch {
      setErrorMessage('Impossible de charger les enseignants.')
      setEnseignants([])
    } finally {
      setIsLoading(false)
    }
  }, [])

  useEffect(() => {
    void Promise.resolve().then(() => {
      loadEnseignants()
    })
  }, [loadEnseignants])

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
      await loadEnseignants()
    } catch {
      setErrorMessage('Impossible de sauvegarder cet enseignant.')
    }
  }

  const handleEdit = async (id) => {
    setErrorMessage('')

    try {
      const response = await getById(id)
      const enseignant = response.data

      setFormValues({
        nom: enseignant.nom ?? '',
        prenom: enseignant.prenom ?? '',
        email: enseignant.email ?? '',
        telephone: enseignant.telephone ?? '',
      })
      setEditingId(id)
    } catch {
      setErrorMessage('Impossible de charger cet enseignant.')
    }
  }

  const handleDelete = async (id) => {
    setErrorMessage('')

    try {
      await remove(id)

      if (editingId === id) {
        resetForm()
      }

      await loadEnseignants()
    } catch {
      setErrorMessage('Impossible de supprimer cet enseignant.')
    }
  }

  return (
    <div className="crud-page">
      <h1 className="page-title">Liste des enseignants</h1>
      {isLoading && <p className="status-loading">Chargement...</p>}
      {errorMessage && <p className="status-error">{errorMessage}</p>}

      <EnseignantForm
        values={formValues}
        isEditing={editingId !== null}
        onChange={handleChange}
        onSubmit={handleSubmit}
        onCancelEdit={resetForm}
      />

      {!isLoading && enseignants.length === 0 ? (
        <p className="status-empty">Aucun enseignant.</p>
      ) : (
        !isLoading && (
          <div className="overflow-x-auto rounded-xl">
            <EnseignantTable
              enseignants={enseignants}
              onEdit={handleEdit}
              onDelete={handleDelete}
            />
          </div>
        )
      )}
    </div>
  )
}

export default EnseignantsPage
