import axios from "./AxiosDecorator";


export default {
    getList() {
        return axios({
            method: `get`,
            url: `/list`
        })
    },
    finished(id) {
        return axios({
            method: `post`,
            url: `/finished/${id}`
        })
    }
}