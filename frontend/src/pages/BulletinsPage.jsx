import { useCallback, useEffect, useMemo, useState } from 'react'
import BulletinForm from '../components/bulletins/BulletinForm'
import BulletinTable from '../components/bulletins/BulletinTable'
import { getAll as getAllEleves } from '../services/eleveService'
import { create, getAll, getById, remove, update } from '../services/bulletinService'

const EMPTY_FORM = {
  idEleve: '',
  trimestre: '',
  anneeScolaire: '',
  moyenneGenerale: '',
  appreciation: '',
}

function BulletinsPage() {
  const [bulletins, setBulletins] = useState([])
  const [eleves, setEleves] = useState([])
  const [formValues, setFormValues] = useState(EMPTY_FORM)
  const [editingId, setEditingId] = useState(null)
  const [isLoading, setIsLoading] = useState(true)
  const [errorMessage, setErrorMessage] = useState('')

  const loadData = useCallback(async () => {
    setIsLoading(true)
    setErrorMessage('')

    try {
      const [bulletinsResponse, elevesResponse] = await Promise.all([
        getAll(),
        getAllEleves(),
      ])

      setBulletins(bulletinsResponse.data)
      setEleves(elevesResponse.data)
    } catch {
      setErrorMessage('Impossible de charger les bulletins.')
      setBulletins([])
      setEleves([])
    } finally {
      setIsLoading(false)
    }
  }, [])

  useEffect(() => {
    void Promise.resolve().then(() => {
      loadData()
    })
  }, [loadData])

  const elevesById = useMemo(
    () => new Map(eleves.map((eleve) => [eleve.idEleve, eleve])),
    [eleves],
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
      trimestre: Number.parseInt(formValues.trimestre, 10),
      anneeScolaire: formValues.anneeScolaire,
      moyenneGenerale:
        formValues.moyenneGenerale === ''
          ? null
          : Number.parseFloat(formValues.moyenneGenerale),
      appreciation: formValues.appreciation,
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
      setErrorMessage('Impossible de sauvegarder ce bulletin.')
    }
  }

  const handleEdit = async (id) => {
    setErrorMessage('')

    try {
      const response = await getById(id)
      const bulletin = response.data

      setFormValues({
        idEleve: bulletin.idEleve?.toString() ?? '',
        trimestre: bulletin.trimestre?.toString() ?? '',
        anneeScolaire: bulletin.anneeScolaire ?? '',
        moyenneGenerale: bulletin.moyenneGenerale?.toString() ?? '',
        appreciation: bulletin.appreciation ?? '',
      })
      setEditingId(id)
    } catch {
      setErrorMessage('Impossible de charger ce bulletin.')
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
      setErrorMessage('Impossible de supprimer ce bulletin.')
    }
  }

  return (
    <div className="crud-page">
      <h1 className="page-title">Liste des bulletins</h1>
      {isLoading && <p className="status-loading">Chargement...</p>}
      {errorMessage && <p className="status-error">{errorMessage}</p>}

      <BulletinForm
        values={formValues}
        eleves={eleves}
        isEditing={editingId !== null}
        onChange={handleChange}
        onSubmit={handleSubmit}
        onCancelEdit={resetForm}
      />

      {!isLoading && bulletins.length === 0 ? (
        <p className="status-empty">Aucun bulletin.</p>
      ) : (
        !isLoading && (
          <div className="overflow-x-auto rounded-xl">
            <BulletinTable
              bulletins={bulletins}
              elevesById={elevesById}
              onEdit={handleEdit}
              onDelete={handleDelete}
            />
          </div>
        )
      )}
    </div>
  )
}

export default BulletinsPage
