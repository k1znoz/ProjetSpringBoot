import { useCallback, useEffect, useState } from 'react'
import ResponsableForm from '../components/responsables/ResponsableForm'
import ResponsableTable from '../components/responsables/ResponsableTable'
import {
  create,
  getAll,
  getById,
  remove,
  update,
} from '../services/responsableService'

const EMPTY_FORM = {
  nom: '',
  prenom: '',
  email: '',
  telephone: '',
}

function ResponsablesPage() {
  const [responsables, setResponsables] = useState([])
  const [formValues, setFormValues] = useState(EMPTY_FORM)
  const [editingId, setEditingId] = useState(null)
  const [isLoading, setIsLoading] = useState(true)
  const [errorMessage, setErrorMessage] = useState('')

  const loadResponsables = useCallback(async () => {
    setIsLoading(true)
    setErrorMessage('')

    try {
      const response = await getAll()
      setResponsables(response.data)
    } catch {
      setErrorMessage('Impossible de charger les responsables.')
      setResponsables([])
    } finally {
      setIsLoading(false)
    }
  }, [])

  useEffect(() => {
    loadResponsables()
  }, [loadResponsables])

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
      await loadResponsables()
    } catch {
      setErrorMessage('Impossible de sauvegarder ce responsable.')
    }
  }

  const handleEdit = async (id) => {
    setErrorMessage('')

    try {
      const response = await getById(id)
      const responsable = response.data

      setFormValues({
        nom: responsable.nom ?? '',
        prenom: responsable.prenom ?? '',
        email: responsable.email ?? '',
        telephone: responsable.telephone ?? '',
      })
      setEditingId(id)
    } catch {
      setErrorMessage('Impossible de charger ce responsable.')
    }
  }

  const handleDelete = async (id) => {
    setErrorMessage('')

    try {
      await remove(id)

      if (editingId === id) {
        resetForm()
      }

      await loadResponsables()
    } catch {
      setErrorMessage('Impossible de supprimer ce responsable.')
    }
  }

  return (
    <div className="crud-page">
      <h1 className="page-title">Liste des responsables</h1>
      {isLoading && <p className="status-loading">Chargement...</p>}
      {errorMessage && <p className="status-error">{errorMessage}</p>}

      <ResponsableForm
        values={formValues}
        isEditing={editingId !== null}
        onChange={handleChange}
        onSubmit={handleSubmit}
        onCancelEdit={resetForm}
      />

      {!isLoading && responsables.length === 0 ? (
        <p className="status-empty">Aucun responsable.</p>
      ) : (
        !isLoading && (
          <div className="overflow-x-auto rounded-xl">
            <ResponsableTable
              responsables={responsables}
              onEdit={handleEdit}
              onDelete={handleDelete}
            />
          </div>
        )
      )}
    </div>
  )
}

export default ResponsablesPage
