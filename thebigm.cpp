

#include "IwNUI.h"
#include "s3e.h"
#include "string.h"
//#include "iostream.h"
#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

using namespace std;
using namespace IwNUI;

class Handler
{
public:
    Handler() 
    {
        IwAssert(NUI, !s_Handler);
        s_Handler = this;
	     counter = 0;
		
//-------------------initialise images-------------------------		 

		 imageviews[0] = "Big_M_oct_Page_01.png";
		 imageviews[1] = "Big_M_oct_Page_02.png";
		 imageviews[2] = "Big_M_oct_Page_03.png";
		 imageviews[3] = "Big_M_oct_Page_04.png";
		 imageviews[4] = "Big_M_oct_Page_05.png";
		 imageviews[5] = "Big_M_oct_Page_06.png";
		 imageviews[6] = "Big_M_oct_Page_07.png";
		 imageviews[7] = "Big_M_oct_Page_08.png";
		 imageviews[8] = "Big_M_oct_Page_09.png";
		 imageviews[9] = "Big_M_oct_Page_10.png";
		 imageviews[10] = "Big_M_oct_Page_11.png";
		 imageviews[11] = "Big_M_oct_Page_12.png";
		 imageviews[12] = "Big_M_oct_Page_13.png";
		 imageviews[13] = "Big_M_oct_Page_14.png";
		 imageviews[14] = "Big_M_oct_Page_15.png";
		 imageviews[15] = "Big_M_oct_Page_16.png";
		 imageviews[16] = "Big_M_oct_Page_17.png";
		 imageviews[17] = "Big_M_oct_Page_18.png";
		 imageviews[18] = "Big_M_oct_Page_19.png";
		 imageviews[19] = "Big_M_oct_Page_20.png";
		 imageviews[20] = "Big_M_oct_Page_21.png";
		 imageviews[21] = "Big_M_oct_Page_22.png";
		 imageviews[22] = "Big_M_oct_Page_23.png";
		 imageviews[23] = "Big_M_oct_Page_24.png";

//----------------label text----------------------


		  labelviews[0] = "samplestr.txt";			
		  labelviews[1] = "samplestr.txt";			
		  labelviews[2] = "samplestr.txt";			
		  labelviews[3] = "DestinyofSounds.txt";
		  labelviews[4] = "DestinyofSounds.txt";
		 labelviews[5] = "DestinyofSounds.txt";
		 labelviews[6] = "DestinyofSounds.txt";
		 labelviews[7] = "DestinyofSounds.txt";
		 labelviews[8] = "DestinyofSounds.txt";
		 labelviews[9] = "DestinyofSounds.txt";
		 labelviews[10] = "DestinyofSounds.txt";
		 labelviews[11] = "DestinyofSounds.txt";
		 labelviews[12] = "DestinyofSounds.txt";
		 labelviews[13] = "DestinyofSounds.txt";
		 labelviews[14] = "DestinyofSounds.txt";
		 labelviews[15] = "DestinyofSounds.txt";
		 labelviews[16] = "DestinyofSounds.txt";
		 labelviews[17] = "DestinyofSounds.txt";
		 labelviews[18] = "DestinyofSounds.txt";
		 labelviews[19] = "DestinyofSounds.txt";
		 labelviews[20] = "DestinyofSounds.txt";
		 labelviews[21] = "DestinyofSounds.txt";
		 labelviews[22] = "DestinyofSounds.txt";
		 labelviews[23] = "DestinyofSounds.txt";



    }

    ~Handler()
    {
        s_Handler = NULL;
		counter=NULL;
    }
//----------------------------------properties------------------------

    static Handler* s_Handler;
    

	//------------image array pointer----------------
	 const char* imageviews[25];

	//-----------------label array pointer-------------
	 const char* labelviews[25];

	 
      

	//----------------Image View Pointer ----------------
	CViewPtr viewimage;
	
	//----------------label View Pointer ----------------
	CViewPtr viewlabel;
	
	
	//----------------image  object-----------
	CImagePtr  image;

	//----------------Label object------------
	CLabelPtr label;

	//---------------button object ------------
	CButtonPtr button1;
	CButtonPtr button2;
	CButtonPtr button3;
	CButtonPtr button4;



	CAppPtr   app;
	
	//--------------gloabal counter for pages--------------
	int counter;


	//-----------------------------------------methods-----------------------------------

	//------------------------- prev pressed--------------------------------
		bool onButton1Click(CButton* button)                      
    { 
			//labelview disabled
			viewlabel->SetAttribute("visible", false);
			label->SetAttribute("visible", false);              

		    image->SetAttribute("visible", false);				//current disabled

			if(counter == 0)									//if first 
			counter=23;											//then previous -> last
			else											    //else in between	
			counter--;											//previous=current-1   

			image->SetAttribute("image",imageviews[counter]);   //change the image atrribute 
            image->SetAttribute("visible", true);				//previous enabled - display

        return true;
    }
    
		 //-------------next pressed--------------------------
    bool onButton2Click(CButton* button)                       
    {
		//----------------------labelview disabled--------------
		viewlabel->SetAttribute("visible", false);         
		label->SetAttribute("visible", false);              

		//------------disable the previous image------------------
		image->SetAttribute("visible", false);				//current disabled

		if(counter == 23)									//if last
		counter=0;											//next is first
		else
		counter++;										    //next=current+1

        	image->SetAttribute("image",imageviews[counter]);   //change the image atrribute 
            image->SetAttribute("visible", true);				//previous enabled - display

        return true;
    }


		   //-----------------read pressed----------------------

	  bool onButton3Click(CButton* button)     
{
	//-------------------File Handling-----------------------

	//const char* temp;
	//static s3eFile*    g_FileHandle;                   // Handle to file which is to be operated on
	//static char *      g_DataToBeRead;
 //   char		   DataToBeRead[4000];
 //   int          labelcounter;
	////	
	// temp = labelviews[counter];

	//s3eFreeBase((void*)g_DataToBeRead);
	//g_FileHandle = s3eFileOpen(temp, "r");

	//

	//	int32 fileSize = s3eFileGetSize(g_FileHandle);
 //       g_DataToBeRead = (char*)s3eMallocBase(fileSize+6+1);
 //       memset(g_DataToBeRead, 0, fileSize + 6);
 //       strcpy(g_DataToBeRead, "`x666666");

	//	s3eFileRead(&g_DataToBeRead[8], fileSize, 1, g_FileHandle);
	//	g_DataToBeRead[fileSize+6] = '\0';

	//
	//	//----------copy file data into char array----------
	//	for(labelcounter=8;labelcounter<4000;labelcounter++)
	//	{
	//	DataToBeRead[labelcounter] =  g_DataToBeRead[labelcounter];
	//	}

	char st[1500]={0};
     int i;
	 char rc;
	  FILE *p;
	  p = fopen(labelviews[counter], "rb");

	      for (i = 0; (rc = getc(p)) != EOF && i < 1400; st[i++] = rc) { }
	
		  fclose(p);

	//-------------  disable the current image-----------------
		button4->SetAttribute("visible", true);	                   // back  to image button enabled
		image->SetAttribute("visible", false);	                  // image disabled
		viewimage->SetAttribute("visible", false);

		//---------------enable the label--------------------
		//label->SetAttribute("caption",labelviews[counter]);			 //   set caption attribute
			label->SetAttribute("caption",st);			 
		viewlabel->SetAttribute("visible", true);
		label->SetAttribute("visible", true);					 //   label enabled -displayy    	

		//---------------disable the buttons-----------------
		button1->SetAttribute("visible", false);
		button2->SetAttribute("visible", false);
		button3->SetAttribute("visible", false);

		 return true;
	  }

	  
//---------------------back to image button pressed------------------------------
bool onButton4Click(CButton* button)                        
    {
		//------------------disable the label ----------------------
		viewlabel->SetAttribute("visible", false);					 
		label->SetAttribute("visible", false);					 


		//------------------enable the image -----------------------
		viewimage->SetAttribute("visible", true);
		image->SetAttribute("visible", true);	                  // current image enabled
		button4->SetAttribute("visible", false);				  //  button disabled	           

		//--------------------enable the buttons-----------------------

		button1->SetAttribute("visible", true);
		button2->SetAttribute("visible", true);
		button3->SetAttribute("visible", true);


		return true;
}


};

//---------------------class ends----------------------------


Handler* Handler::s_Handler = NULL;


//---------------views for images-------------------

CViewPtr CreateimageView()
{
	Handler& handler = *Handler::s_Handler;


	CViewPtr result = CreateView(CAttributes()
        .Set("x1",      "0%")
        .Set("y1",      "0%")
        .Set("width",   "100%")
        .Set("height",  "90%")
        .Set("backgroundColour", CColour(0xffFFFFFF))
        );
    
 //---------------image created--------------------

	CImagePtr image = CreateImage(CAttributes()
        .Set("name",    "Image")
        .Set("x1",      "0%")
        .Set("y1",      "0%")
        .Set("image",   "Big_M_oct_Page_01.png")                  
        .Set("backgroundColour", CColour(0xffFFFFFF)));
        //.Set("alpha",   0.75f));

	result->AddChild(image);
	handler.image=image;

	return result;

}


//-----------------end of image views------------------

//-------------button views-------------------
	
CViewPtr CreatebuttonView()
{
    Handler& handler = *Handler::s_Handler;
    
	CViewPtr result = CreateView(CAttributes()
        .Set("x1", "0%")
        .Set("y1","90%")
        .Set("width", "100%")
        .Set("height", "10%")
        .Set("backgroundColour", CColour(0xff000000))
        );

	CButtonPtr button1 = CreateButton(CAttributes()
        .Set("name",    "Button1")
        .Set("caption", " <-")
		/*.Set("normal",CAttributes().Set("image","left_arrow.png"))
								   .Set("borderX",0)							
								   .Set("borderY",0)
		.Set("pressed",CAttributes().Set("image","left_arrow.png"))
								   .Set("borderX",0)							
								   .Set("borderY",0)*/
		.Set("x1",      "5%")
        .Set("y1",      "6%")
        .Set("width",   "20%")
        .Set("height",  "50%")
        .Set("fontSize", "small"));

		button1->SetEventHandler("click", &handler, &Handler::onButton1Click);
		result->AddChild(button1);
		handler.button1=button1;
    
    CButtonPtr button2 = CreateButton(CAttributes()
        .Set("name",    "Button2")
        .Set("caption", "->")
		/*.Set("normal",CAttributes().Set("image","right_arrow.png"))
								   .Set("borderX",10)							
								   .Set("borderY",10)
		.Set("pressed",CAttributes().Set("image","right_arrow.png"))
								   .Set("borderX",10)							
								   .Set("borderY",10)*/
		.Set("x1",      "55%") 
        .Set("y1",      "6%")
        .Set("width",   "20%")
        .Set("height",  "50%")
        .Set("fontSize", "small"));

		button2->SetEventHandler("click", &handler, &Handler::onButton2Click);
		result->AddChild(button2);
		handler.button2=button2;

		CButtonPtr button3 = CreateButton(CAttributes()
        .Set("name",    "Button3")
        .Set("caption", "Read")
		/*.Set("normal",CAttributes().Set("image","readpage.png"))
								   .Set("borderX",10)							
								   .Set("borderY",10)
		.Set("pressed",CAttributes().Set("image","readpage.png"))
								   .Set("borderX",10)							
								   .Set("borderY",10)*/
		.Set("x1",      "30%")
        .Set("y1",      "6%")
        .Set("width",   "20%")
        .Set("height",  "50%")
        .Set("fontSize", "small"));

		button3->SetEventHandler("click", &handler, &Handler::onButton3Click);
		result->AddChild(button3);
		handler.button3=button3;

		CButtonPtr button4 = CreateButton(CAttributes()
        .Set("name",    "Button4")
        .Set("caption", " ")
		/*.Set("normal",CAttributes().Set("image","back_arrow.PNG"))
								   .Set("borderX",10)							
								   .Set("borderY",10)
		.Set("pressed",CAttributes().Set("image","back_arrow.PNG"))
								   .Set("borderX",10)							
								   .Set("borderY",10)*/
        .Set("x1",      "75%")
        .Set("y1",      "6%")
        .Set("width",   "20%")
        .Set("height",  "50%")
        .Set("fontSize", "small"));

		button4->SetEventHandler("click", &handler, &Handler::onButton4Click);
		result->AddChild(button4);
		handler.button4=button4;

	return result;
};


CViewPtr CreateLabelView()
{
	
	Handler& handler = *Handler::s_Handler;

    CViewPtr result = CreateView(CAttributes()
        .Set("x1","0%")
        .Set("y1","0%")
        .Set("width",   "100%")
        .Set("height",  "90%")
        .Set("backgroundColour","#000000")
        );
    

	//-------------scrolllable----------------------
	CScrollViewPtr scrollView = CreateScrollView(CAttributes()
        .Set("name",    "ScrollView")
        .Set("x1","0%")
        .Set("y1","0%")
        .Set("width",   "100%")
        .Set("height",  "100%")
        .Set("scrollX", 300)
        .Set("scrollY", 300)
        .Set("direction","vertical")
        .Set("backgroundColour", "#FFFFFF"));   //black    0xff000000
        
    result->AddChild(scrollView);


    CLabelPtr label = CreateLabel(CAttributes()
        .Set("name",    "Label")
        .Set("x1",      "5%")
        .Set("y1",      "5%")
        .Set("width",   "95%")
        .Set("height",  "400%")
		.Set("oneline",  false)
        .Set("caption","Hello World")
        .Set("fontSize",    "small")
		.Set("fontColour","#000000")                // CColour(0xab00ffff)
        .Set("fontAlignH", "left")
        .Set("fontAlignV", "top"));  // middle
    
    scrollView->AddChild(label);
	handler.label=label;

    return result;
}



int main()
{
    CAppPtr app = CreateApp();
    CWindowPtr window = CreateWindow();

	 

    Handler handler;
    
    handler.app = app;

	 /*char* imageviews[]={"Image.bmp","button_down.png","test1.png",};
	 char* labelviews[]={"string.txt","string1.txt","string2.txt",};

    handler.imageviews[0] = imageviews[0];
	handler.imageviews[1] = imageviews[1];
	handler.imageviews[2] = imageviews[2];

	handler.labelviews[0]=labelviews[0];
	handler.labelviews[0]=labelviews[1];
	handler.labelviews[0]=labelviews[2];*/


    
    app->AddWindow(window);

	//---------objects of the views---------------
    
    CViewPtr containerView = CreateView();

	CViewPtr buttonView = CreatebuttonView();

	CViewPtr imageView = CreateimageView();

	CViewPtr labelView = CreateLabelView();
    
	labelView->SetAttribute("visible", false);
	buttonView->SetAttribute("visible", true);

	//-----------all the views within the container------------
	containerView->AddChild(buttonView);
	containerView->AddChild(imageView);
	containerView->AddChild(labelView);

	window->SetChild(containerView);

	handler.viewimage=imageView;

	handler.viewlabel=labelView;
	
    app->ShowWindow(window);

    app->Run();
   
    return 0;
}

    
