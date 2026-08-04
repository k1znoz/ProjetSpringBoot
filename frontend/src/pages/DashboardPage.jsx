import { useEffect, useState } from 'react'
import api from '../services/api'

function DashboardPage() {
  const [isApiReachable, setIsApiReachable] = useState(null)
  const [counts, setCounts] = useState({
    eleves: '--',
    classes: '--',
    enseignants: '--',
    responsables: '--',
    matieres: '--',
    notes: '--',
    bulletins: '--',
  })

  const cards = [
    { key: 'eleves', icon: '👨', title: 'Eleves', tone: 'bg-blue-50 text-blue-700' },
    { key: 'classes', icon: '🏫', title: 'Classes', tone: 'bg-slate-100 text-slate-700' },
    {
      key: 'enseignants',
      icon: '🧑‍🏫',
      title: 'Enseignants',
      tone: 'bg-blue-50 text-blue-700',
    },
    {
      key: 'responsables',
      icon: '👪',
      title: 'Responsables',
      tone: 'bg-slate-100 text-slate-700',
    },
    { key: 'matieres', icon: '📚', title: 'Matieres', tone: 'bg-blue-50 text-blue-700' },
    { key: 'notes', icon: '📝', title: 'Notes', tone: 'bg-slate-100 text-slate-700' },
    {
      key: 'bulletins',
      icon: '📄',
      title: 'Bulletins',
      tone: 'bg-blue-50 text-blue-700',
    },
  ]

  useEffect(() => {
    const checkApi = async () => {
      const endpoints = [
        { key: 'eleves', path: '/api/eleves/' },
        { key: 'classes', path: '/api/classes/' },
        { key: 'enseignants', path: '/api/enseignants/' },
        { key: 'responsables', path: '/api/responsables/' },
        { key: 'matieres', path: '/api/matieres/' },
        { key: 'notes', path: '/api/notes/' },
        { key: 'bulletins', path: '/api/bulletins/' },
      ]

      try {
        const results = await Promise.allSettled(endpoints.map(({ path }) => api.get(path)))
        const reachable = results.some((result) => result.status === 'fulfilled')
        setIsApiReachable(reachable)

        const nextCounts = {}
        results.forEach((result, index) => {
          const { key } = endpoints[index]
          if (result.status === 'fulfilled') {
            nextCounts[key] = result.value?.data?.length ?? 0
          } else {
            nextCounts[key] = '--'
          }
        })

        setCounts(nextCounts)
      } catch {
        setIsApiReachable(false)
        setCounts((current) => ({ ...current, eleves: '--' }))
      }
    }

    checkApi()
  }, [])

  return (
    <div className="space-y-6">
      <div>
        <h1 className="page-title">Dashboard</h1>
        <p className="mt-1 text-sm text-slate-600">
          Vue d&apos;ensemble de l&apos;application de gestion scolaire.
        </p>
      </div>

      <div className="grid gap-4 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
        {cards.map((card) => (
          <article
            key={card.title}
            className="rounded-xl border border-slate-200 bg-white p-4 shadow-sm"
          >
            <div className="flex items-center justify-between">
              <span className={`rounded-lg px-2 py-1 text-lg ${card.tone}`}>
                {card.icon}
              </span>
              <span className="text-2xl font-semibold text-slate-900">
                {counts[card.key] ?? '--'}
              </span>
            </div>
            <h2 className="mt-3 text-base font-semibold text-slate-900">{card.title}</h2>
          </article>
        ))}
      </div>

      {isApiReachable === true && (
        <p className="status-loading border-green-200 bg-green-50 text-green-700">
          Connexion API OK
        </p>
      )}
      {isApiReachable === false && (
        <p className="status-error">Impossible de contacter le backend</p>
      )}
      {isApiReachable === null && <p className="status-loading">Chargement...</p>}
    </div>
  )
}

export default DashboardPage
