#include <iostream>
#include <fstream>
#include <vector>
#include <cstdlib>
using namespace std;
void readData(const string &filename, vector<double> &angle, vector<double> &coefOfFlight);
double interpolation(double userAngle, const vector<double> &angle, const vector<double> &coefOfFlight);
bool isOrdered(const vector<double> &angle);
void reorder(vector<double> &angle, vector<double> &coefOfFlight);
int main(int argc, char *argv[])
{
    
    string filename;
    filename=argv[1];
    vector<double> angle;
    vector<double> coefOfFlight;
    readData(filename, angle, coefOfFlight);
    double coef;
    string response= "Yes";
    double userAngle=0;
    while(response=="Yes")
    {
        cout << "Enter a Flight-path angle" << endl;
        cin >> userAngle;
        if(angle.size()>1)
            if(!isOrdered(angle))
                reorder(angle, coefOfFlight);
        coef = interpolation(userAngle, angle, coefOfFlight);
        cout << coef << endl << "Would you like to enter another flight-path angle" << endl;
        cin >> response;
    }
    return 0;
}
void readData(const string &filename, vector<double> &angle, vector<double> &coefOfFlight)
{
    ifstream fin;
    fin.open(filename);
    double a=0;
    double b=0;
    if(!fin.is_open())
    {
        cout << "Error opening " << filename << endl;
        exit(1);
    }
    while(fin >> a >> b)
    {
        angle.push_back(a);
        coefOfFlight.push_back(b);
    }
}

double interpolation(double userAngle, const vector<double> &angle, const vector<double> &coefOfFlight)
{
    for(unsigned int i=0;i<angle.size();i++)
        if(angle.at(i)==userAngle)
            return coefOfFlight.at(i);
    int index=0;
    for(unsigned int i=0;i<angle.size()-1;i++)
        if(angle.at(i)<userAngle && angle.at(i+1)>userAngle)
            index=i;
    double lift=coefOfFlight.at(index)+((userAngle-angle.at(index))/(angle.at(index+1)-angle.at(index)))*(coefOfFlight.at(index+1)-coefOfFlight.at(index));
    return lift;
}

bool isOrdered(const vector<double> &angle)
{
    if(angle.size()<2)
        return true;
    for(unsigned int i=0;i<angle.size()-1;i++)
        if(angle.at(i)>angle.at(i+1))
           return false;
    return true;
}
           
void reorder(vector<double> &angle, vector<double> &coefOfFlight)
{
    double temp;
    for(unsigned int j=0;j<angle.size();j++)
        for(unsigned int i=0;i<angle.size()-1;i++)
            if(angle.at(i)>angle.at(i+1))
            {
              temp=angle.at(i);
              angle.at(i)=angle.at(i+1);
              angle.at(i+1)=temp;
              temp=coefOfFlight.at(i);
              coefOfFlight.at(i)=coefOfFlight.at(i+1);
              coefOfFlight.at(i+1)=temp;
            }
}
