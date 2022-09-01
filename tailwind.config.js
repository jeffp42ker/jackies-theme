module.exports = {
  content: [
    'public/**/*.html',
  ],
  theme: {
    extend: {
      colors: {
        'primary': '#44403c',
        'accent': '#009b50',
        'tertiary': '#E5E5E5',
      },
    },
    fontFamily: {
        'sans': ['Open Sans', 'sans-serif'],
        'serif': ['Domine', 'serif']
    },
  },
  plugins: [
    require('@tailwindcss/forms'),
  ],
}
