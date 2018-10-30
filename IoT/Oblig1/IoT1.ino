int red = 13;
int yellow = 12;
int green = 11;
int PIR = 2;
int b1 = 6;
int b2 = 5;

int b1Pressed = 0;
int b2Pressed = 0;
bool falsePass = true;


void setup()
{
  pinMode(red, OUTPUT);
  pinMode(yellow, OUTPUT);
  pinMode(green, OUTPUT);
  pinMode(PIR, INPUT);
  pinMode(b1, INPUT);
  pinMode(b2, INPUT);
  Serial.begin(9600);
  
}

void loop(){
    while (digitalRead(PIR)){
      delay(300);
      Serial.print("b1: ");
      Serial.print(b1Pressed);
      Serial.print("b2: ");
      Serial.print(b2Pressed);
      Serial.println();

      
      if (falsePass){
       	digitalWrite(yellow, 1);
		digitalWrite(green, 0);
  		digitalWrite(red, 0);
  		enterPass();
    	checkPass();
      }else{
	  digitalWrite(red, 0);
      digitalWrite(yellow, 0);
      digitalWrite(green, 1);
        delay(1000);
        if(digitalRead(b1)||digitalRead(b2)){
          reset();
        }
      }
   	}
  digitalWrite(red, 1);
  digitalWrite(yellow, 0);
  digitalWrite(green, 0);
 }


void enterPass()
{
  
  if (digitalRead(b1)){
    if (b2Pressed==1)
    	b1Pressed = 2;
    else if (b1Pressed==1)
      b1Pressed = 666;
    else
      	b1Pressed = 1;
    digitalWrite(yellow, 0);
    delay(200);
    digitalWrite(yellow, 1);
  }if (digitalRead(b2)){
    if (b1Pressed==1)
    	b2Pressed = 2;
    else if (b2Pressed ==1)
      b2Pressed=666;
    else
      	b2Pressed = 1;
    digitalWrite(yellow, 0);
    delay(200);
    digitalWrite(yellow, 1);
  }
}

void checkPass(){
  if (b1Pressed == 1 && b2Pressed == 2){
	falsePass = false;
  }else if (b1Pressed + b2Pressed >=3){
  	wrongPass();
    reset();
  }
 }

void reset(){
	b1Pressed = 0;
	b2Pressed = 0;
  	falsePass=true;
}

void wrongPass(){
  digitalWrite(red, 0);
  digitalWrite(yellow, 0);
  digitalWrite(green, 0);
  
  digitalWrite(red, 1);
  delay(100);
  digitalWrite(red, 0);
  digitalWrite(yellow, 1);
  delay(100);
  digitalWrite(yellow, 0);
  digitalWrite(red, 1);
  delay(100);
  digitalWrite(red, 0);
  digitalWrite(yellow, 1);
  delay(100);
  digitalWrite(yellow, 0);
  digitalWrite(red, 1);
  delay(100);
  digitalWrite(red, 0);
  digitalWrite(yellow, 1);
  delay(100);
  digitalWrite(yellow, 0);

}