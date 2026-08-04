import { useCallback, useEffect, useState } from 'react'
import MatiereForm from '../components/matieres/MatiereForm'
import MatiereTable from '../components/matieres/MatiereTable'
import {
  create,
  getAll,
  getById,
  remove,
  update,
} from '../services/matiereService'

const EMPTY_FORM = {
  nomMatiere: '',
  coefficient: '',
}

function MatieresPage() {
  const [matieres, setMatieres] = useState([])
  const [formValues, setFormValues] = useState(EMPTY_FORM)
  const [editingId, setEditingId] = useState(null)
  const [isLoading, setIsLoading] = useState(true)
  const [errorMessage, setErrorMessage] = useState('')

  const loadMatieres = useCallback(async () => {
    setIsLoading(true)
    setErrorMessage('')

    try {
      const response = await getAll()
      setMatieres(response.data)
    } catch {
      setErrorMessage('Impossible de charger les matieres.')
      setMatieres([])
    } finally {
      setIsLoading(false)
    }
  }, [])

  useEffect(() => {
    loadMatieres()
  }, [loadMatieres])

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

    const payload = {
      ...formValues,
      coefficient:
        formValues.coefficient === '' ? '' : Number.parseFloat(formValues.coefficient),
    }

    try {
      if (editingId === null) {
        await create(payload)
      } else {
        await update(editingId, payload)
      }

      resetForm()
      await loadMatieres()
    } catch {
      setErrorMessage('Impossible de sauvegarder cette matiere.')
    }
  }

  const handleEdit = async (id) => {
    setErrorMessage('')

    try {
      const response = await getById(id)
      const matiere = response.data

      setFormValues({
        nomMatiere: matiere.nomMatiere ?? '',
        coefficient: matiere.coefficient?.toString() ?? '',
      })
      setEditingId(id)
    } catch {
      setErrorMessage('Impossible de charger cette matiere.')
    }
  }

  const handleDelete = async (id) => {
    setErrorMessage('')

    try {
      await remove(id)

      if (editingId === id) {
        resetForm()
      }

      await loadMatieres()
    } catch {
      setErrorMessage('Impossible de supprimer cette matiere.')
    }
  }

  return (
    <div className="crud-page">
      <h1 className="page-title">Liste des matieres</h1>
      {isLoading && <p className="status-loading">Chargement...</p>}
      {errorMessage && <p className="status-error">{errorMessage}</p>}

      <MatiereForm
        values={formValues}
        isEditing={editingId !== null}
        onChange={handleChange}
        onSubmit={handleSubmit}
        onCancelEdit={resetForm}
      />

      {!isLoading && matieres.length === 0 ? (
        <p className="status-empty">Aucune matiere.</p>
      ) : (
        !isLoading && (
          <div className="overflow-x-auto rounded-xl">
            <MatiereTable
              matieres={matieres}
              onEdit={handleEdit}
              onDelete={handleDelete}
            />
          </div>
        )
      )}
    </div>
  )
}

export default MatieresPage
