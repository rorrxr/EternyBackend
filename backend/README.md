# 프로젝트 기본 세팅
***

1. `src/main/java` 하위 패키지 구조 변경
```plaintext
ex) `com.group.template` → `com.company.eterny`
```

2. SpringBoot Main Class 이름 변경
```plaintext
ex) `TemplateApplication.java` → `ProjectNameApplication.java`
```

3. `build.gradle` Group 변경
```
// build.gradle → Group 변경
// group  'com.group'
group = 'com.company'
version = '0.0.1-SNAPSHOT'
```

4. `settings.gradle` Artifact 변경
```
// settings.gradle → Artifact 변경
// rootProject.name = 'template'
rootProject.name = 'project-name'
```

5. `application.properties` ProjectName 변경
```
// application.properties → ProjectName 변경
// spring.application.name=template
spring.application.name=project-name
```

6. Gradle Refresh 후 실행
   - `MainApplication.java` 에서 `public class MainApplication` 왼쪽 실행 버튼 클릭
