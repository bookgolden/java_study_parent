assertThatThrownBy 异常
    
    assertThatThrownBy(() -> attendance.assureStatus())
            .isInstanceOf(Exception.class)
            .hasMessageContaining("exception context");
            
     assertThatThrownBy(() -> notificationService.notifyNominee())
            .isInstanceOf(TrainingException.class)
            .hasMessageContaining(String.format("training by id {%s} was not found", trainingId));
                                
assertThat.isEqualTo()

    assertThat(actualHistory).isEqualTo(expectedHistory);
    assertThat(1).isEqualTo(1);
    assertThat(actualTraining).isNotNull();
    
assertThat.isTrue()
  
    assertThat(employee.isMale()).isTrue();
    
verify
    
    verify(issueRepo).update(issue);
    verify(changeHistoryRepo).add(isA(ChangeHistory.class));
    verify(issueRepo, never()).update(isA(Issue.class));
    verify(changeHistoryRepo, never()).add(isA(ChangeHistory.class));
    
    
    