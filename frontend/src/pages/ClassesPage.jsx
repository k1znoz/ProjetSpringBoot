import { useCallback, useEffect, useState } from 'react'
import ClasseForm from '../components/classes/ClasseForm'
import ClasseTable from '../components/classes/ClasseTable'
import {
  create,
  getAll,
  getById,
  remove,
  update,
} from '../services/classeService'

const EMPTY_FORM = {
  nomClasse: '',
  niveau: '',
  anneeScolaire: '',
}

function ClassesPage() {
  const [classes, setClasses] = useState([])
  const [formValues, setFormValues] = useState(EMPTY_FORM)
  const [editingId, setEditingId] = useState(null)
  const [isLoading, setIsLoading] = useState(true)
  const [errorMessage, setErrorMessage] = useState('')

  const loadClasses = useCallback(async () => {
    setIsLoading(true)
    setErrorMessage('')

    try {
      const response = await getAll()
      setClasses(response.data)
    } catch {
      setErrorMessage('Impossible de charger les classes.')
      setClasses([])
    } finally {
      setIsLoading(false)
    }
  }, [])

  useEffect(() => {
    void Promise.resolve().then(() => {
      loadClasses()
    })
  }, [loadClasses])

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
      await loadClasses()
    } catch {
      setErrorMessage('Impossible de sauvegarder cette classe.')
    }
  }

  const handleEdit = async (id) => {
    setErrorMessage('')

    try {
      const response = await getById(id)
      const classe = response.data

      setFormValues({
        nomClasse: classe.nomClasse ?? '',
        niveau: classe.niveau ?? '',
        anneeScolaire: classe.anneeScolaire ?? '',
      })
      setEditingId(id)
    } catch {
      setErrorMessage('Impossible de charger cette classe.')
    }
  }

  const handleDelete = async (id) => {
    setErrorMessage('')

    try {
      await remove(id)

      if (editingId === id) {
        resetForm()
      }

      await loadClasses()
    } catch {
      setErrorMessage('Impossible de supprimer cette classe.')
    }
  }

  return (
    <div className="crud-page">
      <h1 className="page-title">Liste des classes</h1>
      {isLoading && <p className="status-loading">Chargement...</p>}
      {errorMessage && <p className="status-error">{errorMessage}</p>}

      <ClasseForm
        values={formValues}
        isEditing={editingId !== null}
        onChange={handleChange}
        onSubmit={handleSubmit}
        onCancelEdit={resetForm}
      />

      {!isLoading && classes.length === 0 ? (
        <p className="status-empty">Aucune classe.</p>
      ) : (
        !isLoading && (
          <div className="overflow-x-auto rounded-xl">
            <ClasseTable
              classes={classes}
              onEdit={handleEdit}
              onDelete={handleDelete}
            />
          </div>
        )
      )}
    </div>
  )
}

export default ClassesPage
