package com.quiz.SuperQuiz.cleanup;

import com.quiz.SuperQuiz.repository.TestResultRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TestResultCleanupService {

    private final TestResultRepository resultRepository;

    public TestResultCleanupService(TestResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    // 🔄 Runs once every night at 12:00 AM (optional safety)
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void deleteOldResults() {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(1);
        resultRepository.deleteByCreatedAtBefore(cutoff);
    }

    // 🔄 Runs every 30 seconds to delete results older than 2 minutes
    @Scheduled(fixedRate = 30 * 1000) // 30,000 ms = 30 sec
    @Transactional
    public void scheduledCleanup() {
        deleteOldTestResults(Duration.ofHours(24));
//        deleteOldTestResults(Duration.ofDays(7));
//        deleteOldTestResults(Duration.ofMinutes(2));
    }

    // ⛔ Internal helper method (not a separate task)
    private void deleteOldTestResults(Duration duration) {
        LocalDateTime cutoff = LocalDateTime.now().minus(duration);
        resultRepository.deleteByCreatedAtBefore(cutoff);
    }
}
