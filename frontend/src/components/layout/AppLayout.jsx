import { NavLink, Outlet, useNavigate } from 'react-router-dom'
import { useAuth } from '../../context/useAuth'

function AppLayout() {
  const { logout } = useAuth()
  const navigate = useNavigate()

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  const navLinkClass = ({ isActive }) =>
    `rounded-lg px-3 py-2 text-sm font-medium transition ${
      isActive
        ? 'bg-blue-600 text-white'
        : 'text-slate-700 hover:bg-blue-100 hover:text-blue-700'
    }`

  return (
    <div className="min-h-screen bg-slate-100 md:flex">
      <aside className="flex flex-col border-b border-slate-200 bg-white p-4 shadow-sm md:min-h-screen md:w-72 md:border-b-0 md:border-r">
        <div>
          <h1 className="mb-4 text-2xl font-semibold tracking-tight text-slate-900">
            Gestion scolaire
          </h1>
          <nav className="grid grid-cols-2 gap-2 md:grid-cols-1">
            <NavLink to="/" end className={navLinkClass}>
              Dashboard
            </NavLink>
            <NavLink to="/eleves" className={navLinkClass}>
              Eleves
            </NavLink>
            <NavLink to="/classes" className={navLinkClass}>
              Classes
            </NavLink>
            <NavLink to="/enseignants" className={navLinkClass}>
              Enseignants
            </NavLink>
            <NavLink to="/responsables" className={navLinkClass}>
              Responsables
            </NavLink>
            <NavLink to="/matieres" className={navLinkClass}>
              Matieres
            </NavLink>
            <NavLink to="/notes" className={navLinkClass}>
              Notes
            </NavLink>
            <NavLink to="/bulletins" className={navLinkClass}>
              Bulletins
            </NavLink>
          </nav>
        </div>

        <button
          type="button"
          onClick={handleLogout}
          className="mt-4 rounded-lg bg-slate-800 px-4 py-2 text-sm font-semibold text-white transition hover:bg-slate-900 md:mt-auto"
        >
          Deconnexion
        </button>
      </aside>

      <main className="flex-1 p-4 md:p-8">
        <Outlet />
      </main>
    </div>
  )
}

export default AppLayout
