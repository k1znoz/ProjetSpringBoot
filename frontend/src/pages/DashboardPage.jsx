import { useEffect, useState } from 'react'
import api from '../services/api'

function DashboardPage() {
  const [isApiReachable, setIsApiReachable] = useState(null)

  const cards = [
    { icon: '👨', title: 'Eleves', count: '--', tone: 'bg-blue-50 text-blue-700' },
    { icon: '🏫', title: 'Classes', count: '--', tone: 'bg-slate-100 text-slate-700' },
    {
      icon: '🧑‍🏫',
      title: 'Enseignants',
      count: '--',
      tone: 'bg-blue-50 text-blue-700',
    },
    {
      icon: '👪',
      title: 'Responsables',
      count: '--',
      tone: 'bg-slate-100 text-slate-700',
    },
    { icon: '📚', title: 'Matieres', count: '--', tone: 'bg-blue-50 text-blue-700' },
    { icon: '📝', title: 'Notes', count: '--', tone: 'bg-slate-100 text-slate-700' },
    {
      icon: '📄',
      title: 'Bulletins',
      count: '--',
      tone: 'bg-blue-50 text-blue-700',
    },
  ]

  useEffect(() => {
    const checkApi = async () => {
      try {
        await api.get('/api/eleves/')
        setIsApiReachable(true)
      } catch {
        setIsApiReachable(false)
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
              <span className="text-2xl font-semibold text-slate-900">{card.count}</span>
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
