package org.example.pages.Admin.enums;

public enum TopBarSection {
    USER_MANAGAMENT("//nav[@aria-label='Topbar Menu']/ul/li[1]"),
    USERS("//nav[@aria-label='Topbar Menu']/ul/li[1]/ul"),

    JOB("//li/span[contains(text(), 'Job ')]"),
    JOB_TITLES("//a[normalize-space()='Job Titles']"),
    PAY_GRADES("//a[normalize-space()='Pay Grades']"),
    EMPLOYMENT_STATUS("//a[normalize-space()='Employment Status']"),
    JOB_CATEGORIES("//a[normalize-space()='Job Categories']"),
    WORK_SHIFTS("//a[normalize-space()='Work Shifts']"),

    ORGANIZATION("//li/span[contains(text(), 'Organization ')]"),
    GENERAL_INFORMATION("//a[normalize-space()='General Information']"),
    LOCATIONS("//a[normalize-space()='Locations']"),
    STRUCTURE("//a[normalize-space()='Structure']"),

    QUALIFICATIONS("//li/span[contains(text(), 'Qualifications ')]"),
    SKILLS("//a[normalize-space()='Skills']"),
    EDUCATIONS("//a[normalize-space()='Skills']"),
    LICENSES("//a[normalize-space()='Licenses']"),
    LANGUAGES("//a[normalize-space()='Languages']"),
    MEMBERSHIPS("//a[normalize-space()='Memberships']"),

    NATIONALITIES("//li/a[contains(text(), 'Nationalities')]"),
    CORPORATE_BRANDIND("//li/a[contains(text(), 'Corporate Branding')]"),

    CONFIGURATION("//li/span[contains(text(), 'Configuration ')]"),
    EMAIL_CONFIGURATION("//a[normalize-space()='Email Configuration']"),
    EMAIL_SUBSCRIPTIONS("//a[normalize-space()='Email Subscriptions']"),
    LOCALIZATION("//a[normalize-space()='Localization']"),
    LANGUAGE_PACKAGES("//a[normalize-space()='Language Packages']"),
    MODULES("//a[normalize-space()='Modules']"),
    SOCIAL_MEDIA_AUTHENTICATION("//a[normalize-space()='Social Media Authentication']"),
    REGISTER_QAUTH_CLIENT("//a[normalize-space()='Register OAuth Client']"),
    LDAP_CONFIGURATION("//a[normalize-space()='LDAP Configuration']");

    private final String topBarSection;

    TopBarSection(String topBarSection) {
        this.topBarSection = topBarSection;
    }

    public String getTopBarSection() {
        return topBarSection;
    }
}
