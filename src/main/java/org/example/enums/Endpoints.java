package org.example.enums;

import lombok.Getter;
public enum Endpoints {
    MAIN("//a[@href='/web/index.php"),
    ADMIN("/admin/viewAdminModule']"),
    PIM("/pim/viewPimModule']"),
    LEAVE("/leave/viewLeaveModule']"),
    TIME("/time/viewTimeModule']"),
    RECRUITMENT("/recruitment/viewRecruitmentModule']"),
    MY_INFO("/pim/viewMyDetails']"),
    PERFORMANCE("/performance/viewPerformanceModule']"),
    DASHBOARD("/dashboard/index']"),
    DIRECTORY("/directory/viewDirectory']"),
    MAINTENANCE("/maintenance/viewMaintenanceModule']"),
    CLAIM("/claim/viewClaimModule']"),
    BUZZ("/buzz/viewBuzz']");

    private final String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}



