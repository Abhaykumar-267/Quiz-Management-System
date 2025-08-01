// import { Component } from '@angular/core';
// import { ActivatedRoute, Router } from '@angular/router';
// import { CommonModule } from '@angular/common';
// import { FormsModule } from '@angular/forms';
// import { AdminService } from '../../services/admin.service';
// import { SharedModule } from '../../../shared/shared.module';

// @Component({
//   selector: 'app-edit-question',
//   standalone: true,
//   imports: [FormsModule, CommonModule ,SharedModule],
//   templateUrl: './edit-question.component.html'
// })
// export class EditQuestionComponent {
//   questionId: number;
//   question: any;

//   constructor(
//     private route: ActivatedRoute,
//     private adminService: AdminService,
//     private router: Router
//   ) {
//     this.questionId = +this.route.snapshot.paramMap.get('id');
//   }

//   ngOnInit(): void {
//     const idParam = this.route.snapshot.paramMap.get('id');

//     if (idParam) {
//       this.questionId = +idParam;

//       this.adminService.getQuestionById(this.questionId).subscribe({
//         next: (res) => {
//           this.question = res;
//           console.log('Fetched Question:', this.question);
//         },
//         error: (err) => {
//           console.error('Failed to load question', err);
//         }
//       });
//     } else {
//       console.error('No question ID in route.');
//     }
//   }

//   updateQuestion(): void {
//     this.adminService.updateQuestion(this.questionId, this.question).subscribe({
//       next: () => {
//         alert('Question updated successfully');
//         this.router.navigate(['/admin/view-test', this.question.testId]); // redirect back
//       },
//       error: err => console.error('Update failed', err)
//     });
//   }
// }





// import { Component, OnInit } from '@angular/core';
// import { ActivatedRoute, Router } from '@angular/router';
// import { CommonModule } from '@angular/common';
// import { FormsModule } from '@angular/forms';
// import { AdminService } from '../../services/admin.service';

// // ✅ NG-ZORRO Modules (for buttons, inputs, form items)
// import { NzButtonModule } from 'ng-zorro-antd/button';
// import { NzInputModule } from 'ng-zorro-antd/input';
// import { NzFormModule } from 'ng-zorro-antd/form';

// @Component({
//   selector: 'app-edit-question',
//   standalone: true,
//   templateUrl: './edit-question.component.html',
//   styleUrls: ['./edit-question.component.scss'],
//   imports: [
//     CommonModule,
//     FormsModule,
//     NzFormModule,
//     NzInputModule,
//     NzButtonModule // ✅ Required for nzType, nz-button
//   ]
// })
// export class EditQuestionComponent implements OnInit {
//   questionId!: number;
//   question: any = {};

//   constructor(
//     private route: ActivatedRoute,
//     private router: Router,
//     private adminService: AdminService
//   ) {}

//   ngOnInit(): void {
//     const id = this.route.snapshot.paramMap.get('id');
//     if (id) {
//       this.questionId = +id;
//       this.adminService.getQuestionById(this.questionId).subscribe({
//         next: (res) => {
//           this.question = res;
//         },
//         error: (err) => {
//           console.error("Failed to load question", err);
//         }
//       });
//     }
//   }

//   updateQuestion(): void {
//     this.adminService.updateQuestion(this.questionId, this.question).subscribe({
//       next: () => {
//         alert('Question updated successfully');
//         this.router.navigate(['/admin/view-test', this.question.testId]);
//       },
//       error: (err) => {
//         console.error('Update failed', err);
//       }
//     });
//   }
// }



// import { Component } from '@angular/core';
// import { ActivatedRoute, Router } from '@angular/router';
// import { AdminService } from '../../services/admin.service';
// import { SharedModule } from '../../../shared/shared.module';

// @Component({
//   selector: 'app-edit-question',
//   standalone: true,
//   imports: [SharedModule],
//   templateUrl: './edit-question.component.html',
//   styleUrl: './edit-question.component.scss'
// })
// export class EditQuestionComponent {
//   question: any = {};
//   questionId: number;
//   testId: number;

//   constructor(
//     private adminService: AdminService,
//     private route: ActivatedRoute,
//     private router: Router
//   ) {}

//   ngOnInit(): void {
//     this.questionId = +this.route.snapshot.paramMap.get('id');
//     this.testId = +this.route.snapshot.queryParamMap.get('testId');

//     this.adminService.getQuestionById(this.questionId).subscribe((res: any) => {
//       this.question = res;
//     });
//     this.getdataquestion();
//   }


//   getdataquestion(){
//     this.adminService.getQuestionById(this.questionId).subscribe({
//       next: (res) => {
//         console.log("🎯 Question Loaded Successfully:", res); // Check here
//         this.question = res;
//       },
//       error: (err) => {
//         console.error("❌ Error occurred while loading question:", err);
//         alert("Error loading question.");
//       }
//     });
//   }
  

//   // updateQuestion(): void {
//   //   this.adminService.updateQuestion(this.questionId, this.question).subscribe({
//   //     next: () => {
//   //       alert('Question updated successfully!');
//   //       this.router.navigate(['/admin/view-test', this.testId]);
//   //     },
//   //     error: (err) => {
//   //       console.error('Update failed:', err);
//   //       alert('Update failed!');
//   //     }
//   //   });
//   // }


// }









import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import { SharedModule } from '../../../shared/shared.module';

@Component({
  selector: 'app-edit-question',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './edit-question.component.html',
  styleUrl: './edit-question.component.scss'
})
export class EditQuestionComponent {
  question: any = {};
  questionId: number;
  testId: number;

  constructor(
    private adminService: AdminService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.questionId = +this.route.snapshot.paramMap.get('id');
   this.testId = +this.route.snapshot.queryParamMap.get('testId');

    this.getQuestionData(this.questionId); // ✅ Load question
  }

  getQuestionData(id:number): void {
    this.adminService.getQuestionById(id).subscribe(data=>{
      console.log(data);
    }
  // next: (res: any) => {
  //   this.question = res;
  //   console.log("✅ Question Loaded:", this.question);
  // },
  // error: (err) => {
  //   console.error("❌ Error occurred while loading question:", err);
  //   alert("Error loading question.");
  // }
);

  }

  // updateQuestion(): void {
  //   this.adminService.updateQuestion(this.questionId, this.question).subscribe({
  //     next: () => {
  //       alert('✅ Question updated successfully!');
  //       this.router.navigate(['/admin/view-test', this.testId]);
  //     },
  //     error: (err) => {
  //       console.error('❌ Update failed:', err);
  //       alert('Update failed!');
  //     }
  //   });
  // }
}
