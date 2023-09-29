import jdk.jshell.Snippet;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class MarsWeatherGUI extends JFrame {

    private JPanel panel;
    private JButton[] daysLabels;
    private JLabel[] daysWeatherInfoLabels;

    private JLabel title;
    private JLabel explanation;
    private JLabel imageSpaceStation;
    private JLabel latestSol;
    private JLabel weather;
    private JLabel windSpeedLabel;
    private JLabel atmosphericPressureLabel;
    private JButton celsiusButton;
    private JButton fahrenheitButton;
    MarsWeatherApiInformation weatherInformationObject;
    private String[] dayNames = new String[7];
    private String[] dayDates = new String[7];

    private int[][][] dayTemps = new int[7][1][3];
    private int[] dayWindSpeeds = new int[7];
    private int[] dayAtmosphericPressures = new int[7];
    private int fahrenheitHigh;
    private int fahrenheitLow;
    private int windSpeed;
    private int atmosphericPressure;

    public MarsWeatherGUI () throws IOException, ParseException {
        super();
        super.setTitle("Mars Weather Application");
        super.setSize(730,480);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setResizable(false);

        this.weatherInformationObject = new MarsWeatherApiInformation();
        this.dayNames = weatherInformationObject.getSol_Keys();
        this.dayDates = weatherInformationObject.getSol_Dates();
        this.dayTemps = weatherInformationObject.getAverage_Temperatures();
        this.dayWindSpeeds = weatherInformationObject.getWind_Speeds();
        this.dayAtmosphericPressures = weatherInformationObject.getAtmospheric_Pressures();
        this.windSpeed = dayWindSpeeds[6];
        this.atmosphericPressure = dayAtmosphericPressures[6];
        this.fahrenheitLow = dayTemps[6][0][1];
        this.fahrenheitHigh = dayTemps[6][0][2];

        buildPanel();
        super.add(panel);
        super.setVisible(true);
    }

    public void buildPanel() {
        this.panel = new JPanel() {
            //create gradient background color
            //purple for the Martian sky
            //and red for the red desert on Mars
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                Color BLUE_ = new Color(100,50,255);
                Color RED_ = new Color(255,50,100);
                GradientPaint gradient = new GradientPaint(0, 0, BLUE_, 0, getHeight(), RED_.darker());
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setLayout(null);

        //labels for the days of the week
        this.daysLabels = new JButton[7];
        int initialXPos = 80;
        for(int i = 0; i < 7; i++) {
            daysLabels[i] = new JButton(dayNames[i]);
            daysLabels[i].setFont(new Font("Agency FB",Font.CENTER_BASELINE,20));
            daysLabels[i].setForeground(Color.lightGray);
            //daysLabels[i].setBackground(Color.DARK_GRAY);
            daysLabels[i].setBounds(initialXPos,292,70,20);
            daysLabels[i].setHorizontalTextPosition(SwingConstants.CENTER);
            daysLabels[i].setOpaque(false);
            daysLabels[i].setContentAreaFilled(false);
            daysLabels[i].setBorderPainted(true);
            daysLabels[i].setFocusPainted(false);
            daysLabels[i].addActionListener(new DaysButtonListener());

            initialXPos += 80;
            panel.add(daysLabels[i]);
        }

        ImageIcon origImage = new ImageIcon("sunny.png");
        Image image = origImage.getImage();
        //resizing of image icon
        Image newImage = image.getScaledInstance(45,45,Image.SCALE_SMOOTH);
        Icon finalImageSun = new ImageIcon(newImage);

        ImageIcon origImageW = new ImageIcon("windy.png");
        Image imageW = origImageW.getImage();
        //resizing of image icon
        Image newImageW = imageW.getScaledInstance(45,45,Image.SCALE_SMOOTH);
        Icon finalImageWind = new ImageIcon(newImageW);

        //weather Labels for the sols (days) of the martian week
        //daily temperature
        //includes an icon of the sun if the day is nice and sunny
        //or an icon of the wind, if the wind speed exceeds 5mph
        //this is the because the average wind speed on Mars is between is fairly weak (1-4mph)
        this.daysWeatherInfoLabels = new JLabel[7];
        int initialXPosition = 80;
        for(int i = 0; i < 7; i++) {

            Icon finalImage = finalImageSun;
            if(dayWindSpeeds[i] >= 6)
                finalImage = finalImageWind;

            this.daysWeatherInfoLabels[i] = new JLabel(dayTemps[i][0][0] + "\u00B0"+"F",finalImage,SwingConstants.CENTER);
            daysWeatherInfoLabels[i].setBounds(initialXPosition,310,70,80);
            daysWeatherInfoLabels[i].setHorizontalTextPosition(SwingConstants.CENTER);
            daysWeatherInfoLabels[i].setVerticalTextPosition(SwingConstants.BOTTOM);
            daysWeatherInfoLabels[i].setIconTextGap(0);
            daysWeatherInfoLabels[i].setFont(new Font("Agency FB",Font.CENTER_BASELINE,20));
            daysWeatherInfoLabels[i].setForeground(Color.gray.brighter());
            daysWeatherInfoLabels[i].setBorder(BorderFactory.createRaisedSoftBevelBorder());

            initialXPosition += 80;
            panel.add(daysWeatherInfoLabels[i]);
        }

        //Title
        this.title = new JLabel("Latest Weather at Elysium Planitia");
        title.setBounds(65,30,600,40);
        title.setFont(new Font("Agency FB",Font.CENTER_BASELINE,35));
        title.setForeground(Color.orange.darker());

        //Explanation of what Mars Insights is
        this.explanation = new JLabel("<html>InSights takes weather measurements (weather, " +
                "<br/>wind, pressure) on the surface of Mars at Elysium, Planitia. " +
                "<br/>A flat, smooth plain near the Mars equator.</html>");
        explanation.setBounds(80,73,500,65);
        explanation.setFont(new Font("Agency FB",Font.BOLD,17));
        explanation.setForeground(Color.black);

        //Icon image of the Mars Insights Station
        ImageIcon imageStation = new ImageIcon("Space-Station.jpg");
        Image imageS = imageStation.getImage();
        //resizing of image icon
        Image newImageS = imageS.getScaledInstance(260,200,Image.SCALE_SMOOTH);
        Icon finalImageSpaceStation = new ImageIcon(newImageS);

        this.imageSpaceStation = new JLabel(finalImageSpaceStation,SwingConstants.CENTER);
        imageSpaceStation.setBounds(430,75,260,200);

        //Latest Recorded Sol (Martian Day)
        this.latestSol = new JLabel("<html>Latest Sol (Martian Day)" +
                "<br/> &emsp; Sol-  " + dayNames[6] + " " +
                "<br/> &emsp; " + dayDates[6] + " </html>",SwingConstants.CENTER);
        latestSol.setBounds(65,150,155,78);
        latestSol.setFont(new Font("Agency FB",Font.CENTER_BASELINE,20));
        latestSol.setForeground(Color.gray.brighter());

        //Weather Temperature of Sol being displayed
        //initially displayed in Celsius
        this.weather = new JLabel("<html>High: " + String.valueOf(fahrenheitHigh) +
                "<br/>Low: " + String.valueOf(fahrenheitLow) + "</html>",SwingConstants.CENTER);
        weather.setBounds(250,155,120,70);
        weather.setFont(new Font("Agency FB",Font.CENTER_BASELINE,30));
        weather.setForeground(Color.gray.brighter());

        //Wind Speed of Sol being displayed
        this.windSpeedLabel = new JLabel("Wind Speed: " + String.valueOf(windSpeed) + " mph");
        windSpeedLabel.setBounds(80,225,200,50);
        windSpeedLabel.setFont(new Font("Agency FB",Font.CENTER_BASELINE,17));
        windSpeedLabel.setForeground(Color.black);

        //Atmospheric Pressure of Sol being displayed
        this.atmosphericPressureLabel = new JLabel("Atmospheric Pressure: " + String.valueOf(atmosphericPressure) + " Pa");
        atmosphericPressureLabel.setBounds(200, 225,200,50);
        atmosphericPressureLabel.setFont(new Font("Agency FB",Font.CENTER_BASELINE,17));
        atmosphericPressureLabel.setForeground(Color.black);

        //Celsius Button to display the temp in Celsius
        this.celsiusButton = new JButton("C");
        celsiusButton.setBounds(340,160,75,50);
        celsiusButton.setFont(new Font("Agency FB",Font.CENTER_BASELINE,25));
        celsiusButton.setForeground(Color.gray);
        celsiusButton.setOpaque(false);
        celsiusButton.setContentAreaFilled(false);
        celsiusButton.setBorderPainted(false);
        celsiusButton.setFocusPainted(false);
        celsiusButton.addActionListener(new CelsiusListener());

        //Fahrenheit Button to display the temp in Fahrenheit
        this.fahrenheitButton = new JButton("F");
        fahrenheitButton.setBounds(385,160,45,50);
        fahrenheitButton.setFont(new Font("Agency FB",Font.CENTER_BASELINE,25));
        fahrenheitButton.setHorizontalTextPosition(SwingConstants.LEFT);
        fahrenheitButton.setForeground(Color.lightGray);
        fahrenheitButton.setOpaque(false);
        fahrenheitButton.setContentAreaFilled(false);
        fahrenheitButton.setBorderPainted(false);
        fahrenheitButton.setFocusPainted(false);
        fahrenheitButton.addActionListener(new CelsiusListener());

        panel.add(title);
        panel.add(explanation);
        panel.add(imageSpaceStation);
        panel.add(latestSol);
        panel.add(weather);
        panel.add(windSpeedLabel);
        panel.add(atmosphericPressureLabel);

        panel.add(fahrenheitButton);
        panel.add(celsiusButton);

    }

    //Button Listener that displays Highest and Lowest temps in Celsius or Fahrenheit
    private class CelsiusListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == celsiusButton) {
                double celsiusHigh = (fahrenheitHigh - 32) * .555;
                double celsiusLow = (fahrenheitLow - 32) * .555;
                weather.setText("<html>High: " + String.valueOf((int)celsiusHigh) +
                        "<br/>Low: " + String.valueOf((int)celsiusLow) + "</html>");

                celsiusButton.setForeground(Color.lightGray);
                fahrenheitButton.setForeground(Color.gray);

            } else if(e.getSource() == fahrenheitButton) {
                weather.setText("<html>High: " + String.valueOf(fahrenheitHigh) +
                        "<br/>Low: " + String.valueOf(fahrenheitLow) + "</html>");

                celsiusButton.setForeground(Color.gray);
                fahrenheitButton.setForeground(Color.lightGray);
            }
        }
    }

    //Button listener that displays relevant information for the sol selected by the user 
    private class DaysButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < 7; i++) {
                if(e.getSource() == daysLabels[i]) {
                    windSpeed = dayWindSpeeds[i];
                    atmosphericPressure = dayAtmosphericPressures[i];
                    fahrenheitLow = dayTemps[i][0][1];
                    fahrenheitHigh = dayTemps[i][0][2];

                    latestSol.setText("<html>Sol  (Martian Day)" +
                            "<br/> &emsp; Sol-  " + dayNames[i] + " " +
                            "<br/> &emsp; " + dayDates[i] + " </html>");

                    weather.setText("<html>High: " + String.valueOf(fahrenheitHigh) +
                            "<br/>Low: " + String.valueOf(fahrenheitLow) + "</html>");

                    windSpeedLabel.setText("Wind Speed: " + String.valueOf(windSpeed) + " mph");

                    atmosphericPressureLabel.setText("Atmospheric Pressure: " + String.valueOf(atmosphericPressure) + " Pa");

                }
            }
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        new MarsWeatherGUI();
    }
}
