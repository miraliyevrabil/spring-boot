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
                <button>Add Book</button>
                <div class="container">
  <div class="row">
    <div class="col-12">
		<table class="table table-image">
		  <thead>
		    <tr>
            <th scope="col">Id</th>
            <th scope="col">Image</th>
		      <th scope="col">Title</th>
		      <th scope="col">Author</th>
		      <th scope="col">Price</th>
              <th scope="col">Old Price</th>
              <th scope="col">Category</th>
              <th scope="col">Language</th>
              <th scope="col">Publishing House</th>
		      <th scope="col">Comments</th>
              <th scope="col">Description</th>
              <th scope="col">In Stock</th>
              <th scope="col">In Stock Amount</th>
              <th scope="col">Published Date</th>
              <th scope="col">Status</th>
              <th scope="col">Tags</th>
              <th scope="col">Book Status</th>
             
		    </tr>
		  </thead>
		  <tbody>
          {
                            this.state.books.map(
                                books => 
                                <tr key={books.id}>
                                    <td> {books.id} </td>
                                    <img src={books.image1} class="img-fluid img-thumbnail" alt=""/>
                                    <td> {books.title} </td>
                                    <td> {books.author} </td>
                                    <td> {books.price} </td>
                                    <td> {books.oldPrice} </td>
                                    <td> {books.category.name} </td>
                                    <td> {books.language.value} </td>
                                    <td> {books.publishingHouse.value} </td>
                                    <td> {books.comments} </td>
                                    <td> {books.description} </td>
                                    <td> {books.inStock} </td>
                                    <td> {books.inStockAmount} </td>
                                    <td> {books.publishedDate} </td>
                                    <td> {books.status} </td>
                                    <td> {books.tags} </td>
                                    <td> {books.bookStatus} </td>
                                </tr>
                            )
                        }
		   
		  </tbody>
		</table>   
    </div>
  </div>
</div>
            </div>
        )
    }
}
export default BooksComponent