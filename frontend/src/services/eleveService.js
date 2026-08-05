import api from './api'

const BASE_PATH = '/api/eleves'

export const getAll = () => api.get(`${BASE_PATH}/`)

export const getById = (id) => api.get(`${BASE_PATH}/${id}`)

export const create = (data) => api.post(`${BASE_PATH}/`, data)

export const update = (id, data) => api.put(`${BASE_PATH}/${id}`, data)

export const remove = (id) => api.delete(`${BASE_PATH}/${id}`)

export const uploadPhoto = (id, file) => {
	const formData = new FormData()
	formData.append('file', file)

	return api.post(`${BASE_PATH}/${id}/photo`, formData, {
		headers: {
			'Content-Type': 'multipart/form-data',
		},
	})
}
