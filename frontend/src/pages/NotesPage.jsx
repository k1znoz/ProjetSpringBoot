import { useCallback, useEffect, useMemo, useState } from 'react'
import NoteForm from '../components/notes/NoteForm'
import NoteTable from '../components/notes/NoteTable'
import { getAll as getAllEleves } from '../services/eleveService'
import { getAll as getAllMatieres } from '../services/matiereService'
import { create, getAll, getById, remove, update } from '../services/noteService'

const EMPTY_FORM = {
  idEleve: '',
  idMatiere: '',
  valeur: '',
  dateNote: '',
  typeEvaluation: '',
  commentaire: '',
}

function NotesPage() {
  const [notes, setNotes] = useState([])
  const [eleves, setEleves] = useState([])
  const [matieres, setMatieres] = useState([])
  const [formValues, setFormValues] = useState(EMPTY_FORM)
  const [editingId, setEditingId] = useState(null)
  const [isLoading, setIsLoading] = useState(true)
  const [errorMessage, setErrorMessage] = useState('')

  const loadData = useCallback(async () => {
    setIsLoading(true)
    setErrorMessage('')

    try {
      const [notesResponse, elevesResponse, matieresResponse] = await Promise.all([
        getAll(),
        getAllEleves(),
        getAllMatieres(),
      ])

      setNotes(notesResponse.data)
      setEleves(elevesResponse.data)
      setMatieres(matieresResponse.data)
    } catch {
      setErrorMessage('Impossible de charger les notes.')
      setNotes([])
      setEleves([])
      setMatieres([])
    } finally {
      setIsLoading(false)
    }
  }, [])

  useEffect(() => {
    loadData()
  }, [loadData])

  const elevesById = useMemo(
    () => new Map(eleves.map((eleve) => [eleve.idEleve, eleve])),
    [eleves],
  )

  const matieresById = useMemo(
    () => new Map(matieres.map((matiere) => [matiere.idMatiere, matiere])),
    [matieres],
  )

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
      idEleve: Number.parseInt(formValues.idEleve, 10),
      idMatiere: Number.parseInt(formValues.idMatiere, 10),
      valeur: Number.parseFloat(formValues.valeur),
      dateNote: formValues.dateNote,
      typeEvaluation: formValues.typeEvaluation,
      commentaire: formValues.commentaire,
    }

    try {
      if (editingId === null) {
        await create(payload)
      } else {
        await update(editingId, payload)
      }

      resetForm()
      await loadData()
    } catch {
      setErrorMessage('Impossible de sauvegarder cette note.')
    }
  }

  const handleEdit = async (id) => {
    setErrorMessage('')

    try {
      const response = await getById(id)
      const note = response.data

      setFormValues({
        idEleve: note.idEleve?.toString() ?? '',
        idMatiere: note.idMatiere?.toString() ?? '',
        valeur: note.valeur?.toString() ?? '',
        dateNote: note.dateNote ?? '',
        typeEvaluation: note.typeEvaluation ?? '',
        commentaire: note.commentaire ?? '',
      })
      setEditingId(id)
    } catch {
      setErrorMessage('Impossible de charger cette note.')
    }
  }

  const handleDelete = async (id) => {
    setErrorMessage('')

    try {
      await remove(id)

      if (editingId === id) {
        resetForm()
      }

      await loadData()
    } catch {
      setErrorMessage('Impossible de supprimer cette note.')
    }
  }

  return (
    <div className="crud-page">
      <h1 className="page-title">Liste des notes</h1>
      {isLoading && <p className="status-loading">Chargement...</p>}
      {errorMessage && <p className="status-error">{errorMessage}</p>}

      <NoteForm
        values={formValues}
        eleves={eleves}
        matieres={matieres}
        isEditing={editingId !== null}
        onChange={handleChange}
        onSubmit={handleSubmit}
        onCancelEdit={resetForm}
      />

      {!isLoading && notes.length === 0 ? (
        <p className="status-empty">Aucune note.</p>
      ) : (
        !isLoading && (
          <div className="overflow-x-auto rounded-xl">
            <NoteTable
              notes={notes}
              elevesById={elevesById}
              matieresById={matieresById}
              onEdit={handleEdit}
              onDelete={handleDelete}
            />
          </div>
        )
      )}
    </div>
  )
}

export default NotesPage
