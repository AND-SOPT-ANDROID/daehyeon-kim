# 3주차 필수 과제

## 과제

- [x] 지금까지 과제로 진행한 뷰에 대해서 실습 때 진행한 플로우를 바탕으로 컴포넌트화 및 UI단 설계를 진행해주세요.
- (홈, MY, 로그인, 회원가입 필수 검색 선택)

| 회원가입                                                                                                                  | 로그인                                                                                                                  | 홈                                                                                                                  | MY                                                                                                                  |
|:----------------------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------|
| <img src="https://github.com/user-attachments/assets/35e22299-a780-4a88-99ef-bec4585481fc" alt="회원가입 화면" width="250"> | <img src="https://github.com/user-attachments/assets/85a24794-65ad-4a7a-b55b-ef2b44c25930" alt="로그인 화면" width="250"> | <img src="https://github.com/user-attachments/assets/5aea81ab-8537-4fe6-8a63-4ccabec821a7" alt="홈 화면" width="250"> | <img src="https://github.com/user-attachments/assets/95901046-fac5-4b1e-9684-b40f70b09749" alt="MY 화면" width="250"> |

<br>

## 1. 공통 컴포넌트 찾기

<img width="1055" alt="공통 컴포넌트" src="https://github.com/user-attachments/assets/209e561b-d266-4705-86e1-50add3691478">

- 공통 컴포넌트 추출 기준은 두 화면이상 공통적으로 보이는 컴포넌트 기준으로 추출해봤습니다.
- text는 text색상별로 컴포넌트화 해보면 어떨까? 하는 생각으로 한번 추출해 봤습니다.
    - ex) `whiteText()` / `grayText()`

<br>

## 2. 뷰 스케치 및 각 화면 컴포넌트화

| 회원가입                                                                                                                     | 로그인                                                                                                                     |
|:-------------------------------------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------|
| <img width="500" alt="회원가입 뷰 스케치" src="https://github.com/user-attachments/assets/41c8e7d1-b2f3-4c62-b351-8c89372f0345"> | <img width="500" alt="로그인 뷰 스케치" src="https://github.com/user-attachments/assets/d5e834d5-0b3e-4aa9-af97-d7cbf5f2ae0e"> |
| 홈                                                                                                                        | MY                                                                                                                      |
| <img width="520" alt="홈 뷰 스케치" src="https://github.com/user-attachments/assets/8a44ef4c-d031-4f53-9cd5-6c266ce64166">    | <img width="480" alt="MY 뷰 스케치" src="https://github.com/user-attachments/assets/d582f671-ee14-432b-a9d0-0f909fc68518">  |

<br>

## 3. UI 로직 설계

| 회원가입                                                                                                                        | 로그인                                                                                                                       |
|:----------------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------|
| <img width="500" alt="회원가입 UI 로직 설계" src="https://github.com/user-attachments/assets/8b15408e-bb35-425f-9e04-88e106bf2fe7"> | <img width="500" alt="로그인 로직설계" src="https://github.com/user-attachments/assets/ee6714b9-e168-424b-9b19-de4b4f845cbb">    |
| 홈                                                                                                                           | MY                                                                                                                        |
| <img width="500" alt="홈 UI 로직 설계" src="https://github.com/user-attachments/assets/1e9b69ef-9c69-4186-9518-dd511d6bd1fe">    | <img width="500" alt="My UI 로직 설계" src="https://github.com/user-attachments/assets/435aaf17-0cd2-49d1-8b57-af92539d550b"> |

<br>

## 4. 공통되는 로직 확장함수화

- 오류메시지 출력 부분 (snackbar)

```kotlin
fun ScaffoldState.showSnackbar(
    scope: CoroutineScope,
    message: String,
    duration: SnackbarDuration = SnackbarDuration.Short
) {
    scope.launch {
        snackbarHostState.showSnackbar(
            message = message,
            duration = duration
        )
    }
}
```
