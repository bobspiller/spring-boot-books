# if running bash
if [ -n "$BASH_VERSION" ]; then
    # include .bashrc if it exists
    if [ -f "$HOME/.bashrc" ]; then
    . "$HOME/.bashrc"
    fi
fi

# set PATH so it includes user's private bin if it exists
if [ -d "$HOME/bin" ] ; then
    PATH="$HOME/bin:$PATH"
fi

set -o vi
alias l='ls -aF'
alias ll='ls -alF'
alias dir='ls -altF'
alias c='clear'
alias h='history'

#
# Make shell a tad more readable (lighter blue on black background)
PS1="\u@\h$ "; export PS1
LS_COLORS=$LS_COLORS:'di=0;35:' ; export LS_COLORS

JAVA_HOME=/opt/java/current
PATH=${JAVA_HOME}/bin:${PATH}

export PATH JAVA_HOME
