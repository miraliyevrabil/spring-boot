import React from "react";
import BookService from "../services/BookService";


class BooksComponent extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            books:[]
        }
    }
    componentDidMount(){
        BookService.getBooks().then((response) => {
            this.setState({books : response.data})
        });
    }
    render(){
        return(
            <div>
                <h1> Books List</h1>
                <table className="table table-stripped">
                    <thead>
                        <tr>
                            <td>Book Id</td>
                            <td>Book Titile</td>
                            <td>Book Author</td>
                            <td>Book Price</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.books.map(
                                books => 
                                <tr key={books.id}>
                                    <td> {books.id} </td>
                                    <td> {books.title} </td>
                                    <td> {books.author} </td>
                                    <td> {books.price} </td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        )
    }
}
export default BooksComponent