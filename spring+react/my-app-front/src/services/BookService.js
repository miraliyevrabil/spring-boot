import axios from 'axios'

const REACT_REST_API_URL = 'http://localhost:8080/api/books';

class BookService {

    getBooks(){
        return axios.get(REACT_REST_API_URL);
    }
}
export default new BookService();