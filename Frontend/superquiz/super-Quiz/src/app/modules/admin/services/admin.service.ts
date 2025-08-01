import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASIC_URL = "http://localhost:8080/";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  createTest(testDto): Observable<any>{
    return this.http.post(BASIC_URL + "api/test/create-test", testDto);
  }

  getAllTest(): Observable<any>{
    return this.http.get(BASIC_URL + "api/test/get-test");
  }

  addQuestionInTest(questionDto): Observable<any>{
    return this.http.post(BASIC_URL + "api/test/add-question", questionDto);
  }

  getAllQuestions(id:number): Observable<any>{
    return this.http.get(BASIC_URL + `api/test/${id}`);
  }

  getAllTestResults(): Observable<any>{
    return this.http.get(BASIC_URL + "api/test/admin-view-result");
  }


  deleteQuestionById(id: number) {
    return this.http.delete(`http://localhost:8080/api/test/questions/${id}`);
  }

  getQuestionById(id: number):Observable<any>  {
    return this.http.get(`http://localhost:8080/api/test/getQuestion/${id}`);
  }


  // getQuestionById(id: number): Observable<any> {
  //   return this.http.get<any>(`http://localhost:8080/api/test/getQuestion/${id}`
  //     , {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/json',
  //       'Accept': 'application/json'
  //     })
  //   });
  // }



  // updateQuestion(id: number, data: any) {
  //     return this.http.put(`http://localhost:8080/api/test/updateQuestion/${id}`, data);
  //   }

}

