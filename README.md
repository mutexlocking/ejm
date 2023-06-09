EJM COMPANY 과제

<공통코드 설계>

1. 범용적인 시스템에서 사용될 수 있는 공통코드와, 각 공통코드가 속하는 공통코드그룹을 설계함. (하나의 공통코드그룹에는 여러개의 공통코드가 속하게 됨)
2. 공통코드그룹과 공통코드는 각각 (한글로 구성된 이름) , (코드값), (상세설명) 으로 구성됨.
3. 먼저 공통코드그룹내에서 이름은 unique성이 보장됨. (즉 공통코드그룹내 이름은 유일한 값) 또한 공통코드내에서도 이름은 unique성이 보장됨. (공통코드내 이름도 유일한 값)
4. 중요한점은, 공통코드그룹내 이름과, 공통코드의 이름도 서로 겹치지 않도록 설계함. (즉 이름은 공통코드그룹과 공통코드를 모두 고려하였을 때, unique한 값)
   EX) 국가 : 대한민국, 미국, ... / 상품분류 : 전자제품, 의류, ... 등의 일반적인 공통코드그룹과 공통코드내 관계를 고려하였기 때문.  
5. 다음으로 공통코드그룹의 코드값은 10이상 99이하의 두자리자연수로 설계함.
6. 공통코드의 코드값은 10000이상 99999이하의 다섯자리 자연수로 설계함. 이중 앞의 두자리인(만의자리와, 천의자리) 10~99 까지가, 자신이 속한 공통코드그룹에 대한 정보를 나타냄.
   그리고 이하 세자리인(백의자리, 십의자리, 일의자리) 001~999까지가, 그 공통코드그룹에 속한 순수한 공통코드값을 지님. 
   EX) 공통코드값이 10001라면, 10값을 지닌 공통코드그룹내에서, 001 값을 지닌 공통코드를 의미함.
7. 중요한점은, 범용적인 시스템의 공통코드라는 점을 감안하여, "자동증가방식"이 아닌, "사용자입력방식"을 채택함.
8. 이러한 공통코드그룹과 공통코드의 코드값은 각각 unique성이 보장됨.
   (공통코드그룹의 코드값 10~99는 공통코드그룹내에서 unique한 값. 공통코드의 코드값 100000~99999도 공통코드내에서 unique한 값)
9. 마지막으로 상세설명은, 각각의 공통코드그룹과 공통코드에 대한 세부적인 내용을 담고 있음.
10. 공통코드그룹과 공통코드의 설계에 대한 상세 내용은 다음의 링크를 방문하여 확인 할 수 있음. (ERD 사진 존재)
   (https://drive.google.com/drive/folders/13480Reebj6SHdSSH35KOlkL2yRrI96SL?usp=sharing)
   (참고: created_at : 각 공통코드 및 공통코드그룹이 생성된 시간 / updated_at : 수정된 시간 / status : ACTIVE, INACTIVE 값 중 하나를 지니며, 논리적인 삭제를 위해 사용됨)


<공통코드 API Sheet>

url : https://docs.google.com/spreadsheets/d/1Me-0xV_-Rbiyfthdoq5-vKH6ynD07yxNo1OHUCA2sd4/edit?usp=sharing

<공통코드 API Spec>


url : https://docs.google.com/spreadsheets/d/1XFlWXCEkupE3GGOKOFwrcVf_ETusVnqciZoGHQDBtiw/edit?usp=sharing
(하단 탭을 통해 API를 선택할 수 있음)

<Project init 관련>

1. DB환경은 AWS RDS의 MySQL 버전 8.028을 사용함. 
2. gradle 버전은 , gradle-7.6.1을 사용함.
3. JDK는 correto-11을 사용함 (그런데 테스트 결과, 해당 설정이 github에 함께 업로드 되지 않아, 다시 clone 받았을 때 JDK 버전이 다를 수 있음. 따라서 로컬 설정 스크린샷 첨부)

- 해당 url에 환경 설정에 대한 스크린샷을 첨부함. (url : https://drive.google.com/drive/folders/13480Reebj6SHdSSH35KOlkL2yRrI96SL?usp=sharing)