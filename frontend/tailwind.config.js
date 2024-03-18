/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {
      colors: {
        yellow: '#FBA834',
        navy: '#333A73',
        lightblue: '#E7E9F7',
        lightgray: '#C7C7C7',
        gray: '#747474',
      },
    },
    fontFamily: {
      TitleBold: ['GmarketSansTTFBold'],
      TitleMedium: ['GmarketSansTTFMedium'],
      TItleLight: ['GmarketSansTTFLight'],
      SubTitle: ['YoonGothic320'],
      Content: ['YoonGothic310'],
    }
  },
  plugins: [],
};