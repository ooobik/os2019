##!/usr/bin/env python3
# vim: set fileencoding=utf8 :
# Näiteprogramm protsessoriaja planeerijate visualiseerimiseks
# algne autor Sten-Oliver Salumaa
# refaktoreerinud ja muidu muutnud Meelis Roos
# Code grabbed from a student - alk

from tkinter import *
import tkinter
from tkinter import ttk
from tkinter import messagebox
import turtle


def puhasta():
    tahvel.delete('all')
    

# joonistaja
def joonista(jarjend):
    puhasta()
    so = 25
    summa = 0
    a = 50
    s= 1250
    e = 0
    f = 50
    g=0
    ees=10
    for i in range(0,50):
        if i in jarjend:
            widget = Label(tahvel, text=i, relief=SUNKEN,width=2, bg="red")
            widget.place(x=50,y=20)
            tahvel.create_window(so+(i*25),30,window=widget)
        else:
            widget = Label(tahvel, text=i, relief=SUNKEN,width=2)
            widget.place(x=50,y=20)
            tahvel.create_window(so+(i*25),30,window=widget)

    x_kordinaadid = [20, 45, 70, 95, 120, 145, 170, 195, 220, 245, 270, 295, 320, 345, 370, 395, 420, 445, 470, 495, 520, 545, 570, 595, 620, 645, 670, 695, 720, 745, 770, 795, 820, 845, 870, 895, 920, 945, 970, 995, 1020, 1045, 1070, 1095, 1120, 1145, 1170, 1195, 1220, 1245]
    for i in jarjend:
        e+=1
        vb = x_kordinaadid[i]
        if e ==1:
            tahvel.create_line(vb,a, vb,f)#x,y,x,y
            tahvel.create_oval(vb, a-3, vb+7, a+4,width = 2, fill = 'black')
            x0 = vb
            a = 50
            f +=20
        else:
            tahvel.create_line(x0,a, vb,f)#x,y,x,y
            tahvel.create_oval(vb, a+15, vb+7, f+5,width = 2, fill = 'black')
            x0=vb
            a +=20
            f +=20

    #Ahhaa! siin oli koodiosa mis arvutas pea läbitud pikkuse
    #Ausalt ei viitsinud seda eraldi funktsiooni kirjutada
    # Häki see siia kommentaari asemele
    return summa

    
    
    

# teeb järjendist kahetasemelise listi, mida on mugavam töödelda
def massiiviks(input_jarjend):
    valjund = []
    valjund = input_jarjend.split(",")
    valjund = [ int(x) for x in valjund ]
    return valjund

# otsustab, millist järjendit teha kahetasemeliseks massiiviks
def massiiviMeister():
    jarjend = []
    if var.get() == 1:
        return massiiviks(predef1)
    elif var.get() == 2:
        return massiiviks(predef2)
    elif var.get() == 3:
        return massiiviks(predef3)
    elif var.get() == 4:
        try:
            return massiiviks(kasutaja_jarjend.get())
        except:
            messagebox.showerror(title="Viga sisendis", message="Vigane kasutaja muster!")
            return massiiviks(predef1)
    else:
        return massiiviks(predef1)


####################################################################
def kasuvalija(jarjend, algoritm):
    if algoritm == "FCFS":
        return FCFS(jarjend)
    elif algoritm == "SSTF":
        return SSTF(jarjend)
    elif algoritm == "SCAN":
        return SCAN(jarjend)
    elif algoritm == "LOOK":
        return LOOK(jarjend)
    
def jooksuta_algoritmi(algoritm):
    jarjend = massiiviMeister()

    valjund = kasuvalija(jarjend, algoritm)
    summa = joonista(valjund)
    keskm_oot = tahvel.create_text(1100, 330, text="Keskmine ooteaeg:  " + str(summa))

# Algorithmid #########################################################################
def FCFS(järjend):

    return None

def SSTF(järjend):

    return None

def LOOK(järjend):

    return None


def SCAN(järjend):

    return None


    
##########################################################################################
predef1 = "2,5,13,29,7,1,18,40,49,4"
predef2 = "1,10,44,2,12,3,13,20"
predef3 = "45,6,16,9,33,28,11,37,49,25"


# GUI
raam = Tk()
raam.title("Planeerimisalgoritmid")
raam.resizable(False, False)
raam.geometry("1300x700")

var = IntVar()
var.set(1)
Radiobutton(raam, text="Esimene", variable=var, value=1).place(x=10,y=40)
Radiobutton(raam, text="Teine", variable=var, value=2).place(x=10,y=70)
Radiobutton(raam, text="Kolmas", variable=var, value=3).place(x=10,y=100)
Radiobutton(raam, text="Enda oma", variable=var, value=4).place(x=10,y=130)

silt_vali = ttk.Label(raam, text="Vali või sisesta järjend (kujul 1,10;4,2;12,3;13,2)")
silt_vali.place(x=10, y=10)

silt1 = ttk.Label(raam, text=predef1)
silt1.place(x=120, y=40)

silt2 = ttk.Label(raam, text=predef2)
silt2.place(x=120, y=70)

silt3 = ttk.Label(raam, text=predef3)
silt3.place(x=120, y=100)

silt_run = ttk.Label(raam, text="Algoritmi käivitamiseks klõpsa nupule")
silt_run.place(x=10, y=160)


kasutaja_jarjend = ttk.Entry(raam)
kasutaja_jarjend.place(x=120, y=135, height=25, width=240)

tahvel = Canvas(raam, width=1300, height=460, background="white")
tahvel.place(x=0, y=320)


LIFO_nupp = ttk.Button(raam, text="FCFS", command = lambda : jooksuta_algoritmi("FCFS"))
LIFO_nupp.place(x=10, y=190,height=40, width=80)

SJF_nupp = ttk.Button(raam, text="SSTF", command = lambda : jooksuta_algoritmi("SSTF"))
SJF_nupp.place(x=100, y=190,height=40, width=80)

SRTF_nupp = ttk.Button(raam, text="SCAN",command = lambda : jooksuta_algoritmi("SCAN"))
SRTF_nupp.place(x=190, y=190,height=40, width=80)

RR_nupp = ttk.Button(raam, text="LOOK",command = lambda : jooksuta_algoritmi("LOOK"))
RR_nupp.place(x=280, y=190,height=40, width=80)



puhasta_nupp = ttk.Button(raam, text="Puhasta väljund", command = lambda : puhasta() )
puhasta_nupp.place(x=500, y=190,height=40, width=150)


raam.mainloop()

