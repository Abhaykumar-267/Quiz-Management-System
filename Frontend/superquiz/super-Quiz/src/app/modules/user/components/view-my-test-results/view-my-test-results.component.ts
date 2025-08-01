import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { TestService } from '../../services/test.service';

@Component({
  selector: 'app-view-my-test-results',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './view-my-test-results.component.html',
  styleUrl: './view-my-test-results.component.scss'
})
export class ViewMyTestResultsComponent {

  dataSet:any;
  
  constructor(private testService: TestService){}

  ngOnInit(){
    this.getTestResults();
  }

  getTestResults() {
    this.testService.getMyTestResults().subscribe(res => {
      const now = new Date();
      this.dataSet = res.filter((result: any) => {
        const createdAt = new Date(result.createdAt);
        const timeDiff = now.getTime() - createdAt.getTime();
        return timeDiff < 2 * 60 * 1000; // ✅ Only last 24 hours
      });
    });
  }


}
