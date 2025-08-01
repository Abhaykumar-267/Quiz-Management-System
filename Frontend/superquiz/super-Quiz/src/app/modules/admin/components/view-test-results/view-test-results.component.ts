import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-view-test-results',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './view-test-results.component.html',
  styleUrl: './view-test-results.component.scss'
})
export class ViewTestResultsComponent {

  resultsData:any;

  constructor(private testService: AdminService){ }

  ngOnInit(){
    this.getAllTestResults();
  }


  getAllTestResults() {
    this.testService.getAllTestResults().subscribe(res => {
      const now = new Date();
      this.resultsData = res.filter((result: any) => {
        const createdAt = new Date(result.createdAt);
        const timeDiff = now.getTime() - createdAt.getTime();
        // return timeDiff < 2 * 60 * 1000; // ✅ Only last 2 minutes
        return timeDiff < 24 * 60 * 60 * 1000; // ✅ Only last 24 hours
      });
    });
  }
  

}
