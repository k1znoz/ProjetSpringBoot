import { useCallback, useEffect, useState } from 'react'
import EleveForm from '../components/eleves/EleveForm'
import EleveTable from '../components/eleves/EleveTable'
import api from '../services/api'
import { create, getAll, getById, remove, update, uploadPhoto } from '../services/eleveService'

const EMPTY_FORM = {
  nom: '',
  prenom: '',
  dateNaissance: '',
  adresse: '',
  email: '',
  telephone: '',
}

const PHOTO_BASE_PATH = '/api/eleves'

const revokeObjectUrl = (url) => {
  if (url) {
    URL.revokeObjectURL(url)
  }
}

const fetchPhoto = (id) => api.get(`${PHOTO_BASE_PATH}/${id}/photo`, {
  responseType: 'blob',
})

function ElevesPage() {
  const [eleves, setEleves] = useState([])
  const [formValues, setFormValues] = useState(EMPTY_FORM)
  const [editingId, setEditingId] = useState(null)
  const [selectedPhotoFile, setSelectedPhotoFile] = useState(null)
  const [photoUrls, setPhotoUrls] = useState({})
  const [editingPhotoUrl, setEditingPhotoUrl] = useState('')
  const [isLoading, setIsLoading] = useState(true)
  const [errorMessage, setErrorMessage] = useState('')

  const loadEleves = useCallback(async () => {
    setIsLoading(true)
    setErrorMessage('')

    try {
      const response = await getAll()
      const loadedEleves = response.data
      const nextPhotoUrls = {}

      await Promise.all(
        loadedEleves
          .filter((eleve) => Boolean(eleve.photoPath))
          .map(async (eleve) => {
            try {
              const photoResponse = await fetchPhoto(eleve.idEleve)
              nextPhotoUrls[eleve.idEleve] = URL.createObjectURL(photoResponse.data)
            } catch {
              nextPhotoUrls[eleve.idEleve] = ''
            }
          }),
      )

      setPhotoUrls((current) => {
        Object.values(current).forEach(revokeObjectUrl)
        return nextPhotoUrls
      })
      setEleves(loadedEleves)
    } catch {
      setErrorMessage('Impossible de charger les eleves.')
      setEleves([])
      setPhotoUrls((current) => {
        Object.values(current).forEach(revokeObjectUrl)
        return {}
      })
    } finally {
      setIsLoading(false)
    }
  }, [])

  useEffect(() => {
    void Promise.resolve().then(() => {
      loadEleves()
    })
  }, [loadEleves])

  useEffect(() => () => {
    Object.values(photoUrls).forEach(revokeObjectUrl)
    revokeObjectUrl(editingPhotoUrl)
  }, [photoUrls, editingPhotoUrl])

  const handleChange = (event) => {
    const { name, value } = event.target
    setFormValues((current) => ({ ...current, [name]: value }))
  }

  const resetForm = () => {
    setFormValues(EMPTY_FORM)
    setEditingId(null)
    setSelectedPhotoFile(null)
    setEditingPhotoUrl((current) => {
      revokeObjectUrl(current)
      return ''
    })
  }

  const handleFileChange = (event) => {
    const file = event.target.files?.[0] ?? null
    setSelectedPhotoFile(file)
  }

  const handleSubmit = async (event) => {
    event.preventDefault()
    setErrorMessage('')

    try {
      let savedEleveId = editingId

      if (editingId === null) {
        const response = await create(formValues)
        savedEleveId = response.data?.idEleve ?? null
      } else {
        await update(editingId, formValues)
      }

      if (selectedPhotoFile && savedEleveId !== null) {
        await uploadPhoto(savedEleveId, selectedPhotoFile)
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
      setSelectedPhotoFile(null)

      setEditingPhotoUrl((current) => {
        revokeObjectUrl(current)
        return ''
      })

      if (eleve.photoPath) {
        try {
          const photoResponse = await fetchPhoto(id)
          setEditingPhotoUrl(URL.createObjectURL(photoResponse.data))
        } catch {
          setEditingPhotoUrl('')
        }
      }
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
        photoPreviewUrl={editingPhotoUrl}
        selectedPhotoName={selectedPhotoFile?.name ?? ''}
        onChange={handleChange}
        onFileChange={handleFileChange}
        onSubmit={handleSubmit}
        onCancelEdit={resetForm}
      />

      {!isLoading && eleves.length === 0 ? (
        <p className="status-empty">Aucun élève.</p>
      ) : (
        !isLoading && (
          <div className="overflow-x-auto rounded-xl">
            <EleveTable
              eleves={eleves}
              photoUrls={photoUrls}
              onEdit={handleEdit}
              onDelete={handleDelete}
            />
          </div>
        )
      )}
    </div>
  )
}

export default ElevesPage
