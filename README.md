First run movie-seller app, then movie-analyzer.  
Because the database is created in the first app and the second is connected

---

#### Create user URL

Post: http://localhost:8081/api/v1/customer  
Body:  
{  
"username": "username1",  
"email": "test1@gmail.com",  
"password": "password"  
}

---

#### Create movie URL

Post: http://localhost:8081/api/v1/movie  
Body:  
{  
"title": "Attack on titan",  
"movieGenre": "fantasy",  
"description": "Anime",  
"activeStatus": true,  
"rating": 8.5,  
"releaseDate": "3901-01-10T22:00:00.000+00:00"  
}

---

#### Create order URL

Post: http://localhost:8081/api/v1/order  
Body:  
{  
"customerId": 1,  
"purchasedMovie": 1  
}

---

#### Get all customers

Get: http://localhost:8081/api/v1/customer/customers

---

#### Get customer by id

Get: http://localhost:8081/api/v1/customer/1

---

#### Get All movies

Get: http://localhost:8081/api/v1/movie/movies

---

#### Disable movie by id

Patch: http://localhost:8081/api/v1/movie/disable?movieId=1

---

#### Enable movie by id

Patch: http://localhost:8081/api/v1/movie/enable?movieId=1

---
