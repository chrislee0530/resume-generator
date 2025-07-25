# RESUME Generator

## Project Description

Nowadays, a professional, concise, and well-structured resume is essential for every undergraduate student–whether their post-graduate goal is employment at one of FAANG companies or further education at graduate school.  Recently, I learned the essential components of resume. A resume must “summarize your professional work experience, skills, and educational history in a one one or two-page document” (Indeed, 2024). Unfortunately, many people overlook these significant components, which can lead to missed opportunities in securing a job or admission to graduate programs. My project aims to address this issue.

The users will get to enter their information in response to specific prompts. At the end of the process, the program will compile all inputted information and generate a professional resume. Although this user-friendly program can be utilized by individuals of any age, its primary targeted user segment consists of undergraduate students and recent graduates who need a professional resume for their post-graduate journey. This program is especially meaningful to me as an undergraduate student, as I understand the importance of having a strong resume. With this project, I wish to help others (including myself) reduce the stress and time spent manually formatting resumes, allowing them to focus on other valuable things.
 
## User Stories

- As a user, I want to be able to add my **sections** (profile, experience, education, skills) and add them to my **resume**
- As a user, I want to be able to select a **section** and view the list of contents (ex. a list of skills on the Skill section) that I have added on my resume
- As a user, I want to select a **section** and edit the information
- As a user, I want to delete a **section** from my resume
- As a user, I want to view the final version of my resume
- As a user, when I select the quit option from the application menu, I want to be reminded to save all resume information (educations, experiences, skills, etc.) to file and have the option to do so or not
- As a user, when I start the application, I want to be given the option to load my resume information from file.

## Instructions for End User

1. Start by clicking Add profile to add your profile to resume
2. Fill out your profile information in each text field
3. Add experience, education, and skills by clikcing buttons at the top of the application
4. If you want to remove all experiences, educations, and list of skills, click the button accordingly
   at the top of the application and click 'remove ALL' button
5. To reorder your experience based on the level of your skills, click the Skills button to open up the 
   skills panel and click 'Reorder Skills' button
6. To generate resume, click 'Generate Resume' button at the top-right corner of the application

- To save your resume, click the file button at the top-left corner and click 'save' button
- To load your resume, click the file button at the top-left corner and click 'load' button

## Phase 4: Task 2
Sat Mar 29 03:09:09 PDT 2025
A new profile added to resume!
Sat Mar 29 03:09:30 PDT 2025
A new experience added to resume!
Sat Mar 29 03:09:40 PDT 2025
A new education added to resume!
Sat Mar 29 03:09:48 PDT 2025
A new skill added to resume!
Sat Mar 29 03:09:53 PDT 2025
A new skill added to resume!
Sat Mar 29 03:09:58 PDT 2025
A new skill added to resume!
Sat Mar 29 03:10:00 PDT 2025
Skills reordered from highest to lowest level!
Sat Mar 29 03:10:00 PDT 2025
A new skill added to resume!
Sat Mar 29 03:10:00 PDT 2025
A new skill added to resume!
Sat Mar 29 03:10:00 PDT 2025
A new skill added to resume!
Sat Mar 29 03:10:02 PDT 2025
All skills removed from resume!
Sat Mar 29 03:10:06 PDT 2025
All educations removed from resume!


## Phase 4: Task 3
If I had more time with this project, there are many refactoring I would use to improve the design. First of all, I would definitely make make one abstract class "Component" which Education, Experience, and Skill class can all extend to. The reasoning behind this refactoring is two folds - (1) there are so many duplicate codes in Education, Experience, and Skill class such as all setters and getters which could be refactored into just one abstract class and (2) because of this redundancy, it would be very inefficient if I wanted to add another component to resume such as Awards or Accomplishment section as I would have to write all getters and setters again in each class. This refactoring will also reduce significant amount of redundancy in ResumeApp methods because if these were refacotred, that there can be just one method addComponent instead of addEducation, addExperience, and addSkill all separate

Secondly, I would get rid of all classes that represent lists of a class such as EducaitonList class and ExperienceList class. Instead, I would just simple have fields in resume that would be something like ArrayList<Experience> or ArrayList<Education>. This will reduce the complexity of the project because the current model has a list of list which is extremely unnecessary to have considering the purpose and usage of the list. Lastly, I would refactor the mostRecentExperience and mostRecentEducation method into just one method mostRecent in resume where the list of Education and Experience will be. Since those two methods are fundamentally exact same methods but just dealing with either Experience or Education, this refactoring will reduce the unnecessary redundancy in my code.