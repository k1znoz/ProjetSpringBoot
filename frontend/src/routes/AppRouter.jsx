import { Route, Routes } from 'react-router-dom'
import AppLayout from '../components/layout/AppLayout'
import BulletinsPage from '../pages/BulletinsPage'
import ClassesPage from '../pages/ClassesPage'
import DashboardPage from '../pages/DashboardPage'
import ElevesPage from '../pages/ElevesPage'
import EnseignantsPage from '../pages/EnseignantsPage'
import LoginPage from '../pages/LoginPage'
import MatieresPage from '../pages/MatieresPage'
import NotesPage from '../pages/NotesPage'
import ResponsablesPage from '../pages/ResponsablesPage'
import ProtectedRoute from './ProtectedRoute'

function AppRouter() {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route element={<ProtectedRoute />}>
        <Route element={<AppLayout />}>
          <Route path="/" element={<DashboardPage />} />
          <Route path="/classes" element={<ClassesPage />} />
          <Route path="/bulletins" element={<BulletinsPage />} />
          <Route path="/eleves" element={<ElevesPage />} />
          <Route path="/enseignants" element={<EnseignantsPage />} />
          <Route path="/matieres" element={<MatieresPage />} />
          <Route path="/notes" element={<NotesPage />} />
          <Route path="/responsables" element={<ResponsablesPage />} />
        </Route>
      </Route>
    </Routes>
  )
}

export default AppRouter
