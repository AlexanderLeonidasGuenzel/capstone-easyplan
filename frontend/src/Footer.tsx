import './Footer.css'

export default function Footer() {

    const myName = "Alexander Leonidas Guenzel"
    const company = "Neue Fische GmbH"
    const year = new Date().getFullYear();
    const course = "Java Fullstack Bootcamp";
    const line = " | ";

  return (
    <>
        <div className="footer">
            <p><span className="orange">©{year}</span> {myName.toUpperCase()}
                <span className="orange">{line}{company.toUpperCase()}{line}</span>
                {course.toUpperCase()}</p>
        </div >
    </>
  )
}

