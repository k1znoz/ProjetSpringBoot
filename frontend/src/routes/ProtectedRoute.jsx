import { Navigate, Outlet } from 'react-router-dom'
import { useAuth } from '../context/useAuth'

function ProtectedRoute() {
  const { isAuthenticated } = useAuth()

  if (isAuthenticated) {
    return <Outlet />
  }

  return <Navigate to="/login" replace />
}

export default ProtectedRoute
