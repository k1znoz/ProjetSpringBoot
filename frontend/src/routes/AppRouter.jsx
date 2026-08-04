import { Route, Routes } from 'react-router-dom'
import LoginPage from '../pages/LoginPage'
import NotFoundPage from '../pages/NotFoundPage'

function AppRouter() {
  return (
    <Routes>
      <Route path="/" element={<LoginPage />} />
      <Route path="*" element={<NotFoundPage />} />
    </Routes>
  )
}

export default AppRouter
