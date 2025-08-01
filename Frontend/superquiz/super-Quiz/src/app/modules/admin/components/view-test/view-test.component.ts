import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { AdminService } from '../../services/admin.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-view-test',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './view-test.component.html',
  styleUrl: './view-test.component.scss'
})
export class ViewTestComponent {

  questions: any[] = [];
  testId: any;

  constructor(
    private adminService: AdminService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      this.testId = +params.get('id');
      this.loadQuestions();
    });
  }

  loadQuestions() {
    this.adminService.getAllQuestions(this.testId).subscribe(res => {
      this.questions = res.questions;
      console.log(this.questions);
    });
  }

  deleteQuestion(questionId: number): void {
    this.adminService.deleteQuestionById(questionId).subscribe({
      next: () => {
        // Remove from UI
        this.questions = this.questions.filter(q => q.id !== questionId);
      },
      error: (error) => {
        console.error('Error deleting question:', error);
      }
    });
  }

  editQuestion(id: number): void {
    this.router.navigate(['/admin/edit-question', id]);
  }
  

}
